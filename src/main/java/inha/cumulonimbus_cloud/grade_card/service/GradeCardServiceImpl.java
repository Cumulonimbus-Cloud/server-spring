package inha.cumulonimbus_cloud.grade_card.service;

import inha.cumulonimbus_cloud.common.exceptions.BaseException;
import inha.cumulonimbus_cloud.grade_card.controller.dto.response.PatchGradeCardRes;
import inha.cumulonimbus_cloud.grade_card.controller.dto.response.PostGradeCardRes;
import inha.cumulonimbus_cloud.user.domain.User;
import inha.cumulonimbus_cloud.user.domain.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

import static inha.cumulonimbus_cloud.common.code.status.ErrorStatus.NOT_FIND_USER;
import static inha.cumulonimbus_cloud.common.code.status.ErrorStatus.S3_UPLOAD_ERROR;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class GradeCardServiceImpl implements GradeCardService {

    private final UserJpaRepository userJpaRepository;

    @Value("${flask.url}")
    private String flaskServerUrl;

    @Override
    public PostGradeCardRes uploadGradeCard(MultipartFile file, Long userId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = flaskServerUrl + "/upload_grade_card";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        try {
            body.add("file", new MultipartFileResource(file));
        } catch (IOException e) {
            throw new BaseException(S3_UPLOAD_ERROR);
        }
        body.add("userId", userId.toString());
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Map.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            Map<String, String> responseBody = (Map<String, String>) response.getBody();
            User user = userJpaRepository.findById(userId).orElseThrow(() -> new BaseException(NOT_FIND_USER));
            String gradCardUrl = responseBody.get("url");
            user.setGradeCardUrl(gradCardUrl);
            return PostGradeCardRes.builder()
                    .gradeGardUrl(gradCardUrl)
                    .hasGradeCard(user.isHasGradeCard())
                    .gradeCardDate(user.getGradeCardDate())
                    .build();
        } else {
            throw new BaseException(S3_UPLOAD_ERROR);
        }
    }

    @Override
    public PatchGradeCardRes updateGradeCard(MultipartFile file, Long userId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = flaskServerUrl + "/upload_grade_card";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        try {
            body.add("file", new MultipartFileResource(file));
        } catch (IOException e) {
            throw new BaseException(S3_UPLOAD_ERROR);
        }
        body.add("userId", userId.toString());
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Map.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            Map<String, String> responseBody = (Map<String, String>) response.getBody();
            User user = userJpaRepository.findById(userId).orElseThrow(() -> new BaseException(NOT_FIND_USER));
            String gradCardUrl = responseBody.get("url");
            user.setGradeCardUrl(gradCardUrl);
            return PatchGradeCardRes.builder()
                    .gradeGardUrl(gradCardUrl)
                    .hasGradeCard(user.isHasGradeCard())
                    .gradeCardDate(user.getGradeCardDate())
                    .build();
        } else {
            throw new BaseException(S3_UPLOAD_ERROR);
        }
    }

    public static class MultipartFileResource extends ByteArrayResource {
        private final String filename;

        public MultipartFileResource(MultipartFile multipartFile) throws IOException {
            super(multipartFile.getBytes());
            this.filename = multipartFile.getOriginalFilename();
        }

        @Override
        public String getFilename() {
            return this.filename;
        }
    }
}
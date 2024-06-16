package inha.cumulonimbus_cloud.api.service.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import inha.cumulonimbus_cloud.api.controller.chat.dto.request.PostChatReq;
import inha.cumulonimbus_cloud.api.controller.chat.dto.response.PostChatRes;
import inha.cumulonimbus_cloud.common.exceptions.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static inha.cumulonimbus_cloud.common.code.status.ErrorStatus.JSON_CONVERT_ERROR;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ChatServiceImpl implements ChatService {

    @Override
    public PostChatRes getAnswer(PostChatReq postChatReq) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Flask 서버로 요청을 보낼 JSON 데이터 생성
        String json;
        try {
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("question", postChatReq.getQuestion());
            json = new ObjectMapper().writeValueAsString(requestBody);
        } catch (JsonProcessingException e) {
            throw new BaseException(JSON_CONVERT_ERROR);
        }

        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:5000/ask", HttpMethod.POST, entity, String.class);

        // Flask 서버의 응답을 JSON 객체로 처리
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> responseMap;
        try {
            responseMap = objectMapper.readValue(responseBody, Map.class);
        } catch (JsonProcessingException e) {
            throw new BaseException(JSON_CONVERT_ERROR);
        }

        String answer = (String) responseMap.get("response");

        // Flask 서버의 응답 반환
        return PostChatRes.builder().answer(answer).build();
    }
}
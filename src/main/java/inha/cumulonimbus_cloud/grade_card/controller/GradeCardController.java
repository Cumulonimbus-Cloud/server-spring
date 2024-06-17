package inha.cumulonimbus_cloud.grade_card.controller;

import inha.cumulonimbus_cloud.common.BaseResponse;
import inha.cumulonimbus_cloud.grade_card.controller.dto.response.PostGradeCardRes;
import inha.cumulonimbus_cloud.grade_card.service.GradeCardService;
import inha.cumulonimbus_cloud.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static inha.cumulonimbus_cloud.common.code.status.SuccessStatus.POST_GRADE_OK;

@Slf4j
@Tag(name = "grade_card controller", description = "성적표 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/grade_card")
public class GradeCardController {

    private final GradeCardService gradeCardService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    @Operation(summary = "성적표 크롤링 API", description = "성적표를 크롤링하여 csv파일로 저장합니다.")
    public BaseResponse<PostGradeCardRes> uploadGradeCard(@AuthenticationPrincipal User user,
                                                          @RequestParam("file") MultipartFile file) {
        return BaseResponse.of(POST_GRADE_OK, gradeCardService.uploadGradeCard(file, user.getId()));
    }
}

package inha.cumulonimbus_cloud.grade_card.controller;

import inha.cumulonimbus_cloud.grade_card.service.GradeCardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "grade_card controller", description = "성적표 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/grade_card")
public class GradeCardController {

    private final GradeCardService gradeCardService;


}

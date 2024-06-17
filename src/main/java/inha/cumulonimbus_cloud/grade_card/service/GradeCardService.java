package inha.cumulonimbus_cloud.grade_card.service;

import inha.cumulonimbus_cloud.grade_card.controller.dto.response.PostGradeCardRes;
import org.springframework.web.multipart.MultipartFile;

public interface GradeCardService {

    PostGradeCardRes uploadGradeCard(MultipartFile file, Long userId);
}

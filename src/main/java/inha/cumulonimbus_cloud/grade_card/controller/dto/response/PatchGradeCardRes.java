package inha.cumulonimbus_cloud.grade_card.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatchGradeCardRes {

    @NotNull
    @Schema(description = "성적표 url", example = "http://~~")
    private String gradeGardUrl;

    @NotNull
    @Schema(description = "성적표 입력 여부", example = "true")
    private boolean hasGradeCard;

    @NotNull
    @Schema(description = "성적표 입력 일자", example = "2024-06-17")
    private String gradeCardDate;
}

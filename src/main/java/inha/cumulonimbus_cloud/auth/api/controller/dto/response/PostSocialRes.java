package inha.cumulonimbus_cloud.auth.api.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostSocialRes {

    @NotNull
    @Schema(description = "유저 아이디", example = "1")
    private Long id;

    @Schema(description = "성적표 입력 여부", example = "true")
    private boolean hasGradeCard;

    @Schema(description = "엑세스 토큰", example = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJjdW11bG9uaW1idXNAY3VtdWxvbmltYnVzLmNvbSIsInN1YiI6IjMyNTExMzE0MTEiLCJpYXQiOjE3MTg1MzI2ODYsImV4cCI6MTcxOTM5NjY4Nn0.m2-VWh7-wjRHqDmu9pweuqPYo9CvmPVAepFE2wUd1rc")
    @NotNull
    private String accessToken;
}

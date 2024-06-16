package inha.cumulonimbus_cloud.api.controller.auth.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostSocialRes {

    @NotNull
    private Long id;

    private boolean isFinished;
    @NotNull
    private String accessToken;
}

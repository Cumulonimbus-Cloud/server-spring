package inha.cumulonimbus_cloud.chat.api.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PostChatReq {

    @Schema(description = "유저의 질문", example = "내가 안들은 필수 과목들이 뭐야?")
    @NotNull
    private String question;
}

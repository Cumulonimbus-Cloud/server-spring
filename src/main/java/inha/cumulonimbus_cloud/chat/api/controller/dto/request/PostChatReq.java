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

    @Schema(description = "유저의 질문", example = "1")
    @NotNull
    private String chatId;

    @Schema(description = "chatId가 6번이 아니면 null, 6번이면 질문 내용을 담아 기타 자유 질문", example = "컴퓨터 공학과 졸업 요건이 뭐야?")
    private String etc;
}

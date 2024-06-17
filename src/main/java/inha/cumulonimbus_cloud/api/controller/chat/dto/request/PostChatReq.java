package inha.cumulonimbus_cloud.api.controller.chat.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PostChatReq {

    private String question;
    private Long userId;
}

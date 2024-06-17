package inha.cumulonimbus_cloud.api.controller.chat;

import inha.cumulonimbus_cloud.api.controller.chat.dto.request.PostChatReq;
import inha.cumulonimbus_cloud.api.controller.chat.dto.response.GetChatRes;
import inha.cumulonimbus_cloud.api.controller.chat.dto.response.PostChatRes;
import inha.cumulonimbus_cloud.api.service.chat.ChatService;
import inha.cumulonimbus_cloud.common.BaseResponse;
import inha.cumulonimbus_cloud.domain.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static inha.cumulonimbus_cloud.common.code.status.SuccessStatus.GET_CHAT_HISTORY_OK;
import static inha.cumulonimbus_cloud.common.code.status.SuccessStatus.POST_CHAT_OK;

@Slf4j
@Tag(name = "chat controller", description = "chat API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {

    private final ChatService chatService;
    @PostMapping
    @Operation(summary = "챗봇 질문 API", description = "챗봇 질문을 받아 답변을 제공합니다.")
    public BaseResponse<PostChatRes> chat(@AuthenticationPrincipal User user, @RequestBody PostChatReq postChatReq) {
        return BaseResponse.of(POST_CHAT_OK, chatService.getAnswer(user.getId(), postChatReq));
    }

    @GetMapping
    @Operation(summary = "챗봇 대화 내역 조회 API", description = "챗봇 대화 내역을 조회합니다.")
    public BaseResponse<GetChatRes> getChatHistory(@AuthenticationPrincipal User user) {
        return BaseResponse.of(GET_CHAT_HISTORY_OK, chatService.getChatHistory(user.getId()));
    }
}

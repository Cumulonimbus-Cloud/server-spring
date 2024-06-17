package inha.cumulonimbus_cloud.api.controller.chat;

import inha.cumulonimbus_cloud.api.controller.chat.dto.request.PostChatReq;
import inha.cumulonimbus_cloud.api.controller.chat.dto.response.GetChatRes;
import inha.cumulonimbus_cloud.api.controller.chat.dto.response.PostChatRes;
import inha.cumulonimbus_cloud.api.service.chat.ChatService;
import inha.cumulonimbus_cloud.common.BaseResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "chat controller", description = "chat API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {

    private final ChatService chatService;
    @PostMapping
    public BaseResponse<PostChatRes> chat(@RequestBody PostChatReq postChatReq) {
        return BaseResponse.onSuccess(chatService.getAnswer(postChatReq));
    }

    @GetMapping
    public BaseResponse<GetChatRes> getChatHistory(@RequestParam("userId") Long userId) {
        return BaseResponse.onSuccess(chatService.getChatHistory(userId));
    }
}
package inha.cumulonimbus_cloud.chat.api.service;

import inha.cumulonimbus_cloud.chat.api.controller.dto.request.PostChatReq;
import inha.cumulonimbus_cloud.chat.api.controller.dto.response.GetChatRes;
import inha.cumulonimbus_cloud.chat.api.controller.dto.response.PostChatRes;

public interface ChatService {

    PostChatRes getAnswer(Long userId, PostChatReq postChatReq);

    GetChatRes getChatHistory(Long userId);
}

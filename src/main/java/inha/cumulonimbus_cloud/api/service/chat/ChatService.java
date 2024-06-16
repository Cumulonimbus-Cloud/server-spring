package inha.cumulonimbus_cloud.api.service.chat;

import inha.cumulonimbus_cloud.api.controller.chat.dto.request.PostChatReq;
import inha.cumulonimbus_cloud.api.controller.chat.dto.response.PostChatRes;
import inha.cumulonimbus_cloud.common.BaseResponse;

public interface ChatService {

    PostChatRes getAnswer(PostChatReq postChatReq);
}

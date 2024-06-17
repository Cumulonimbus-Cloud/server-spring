package inha.cumulonimbus_cloud.chat.api.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class GetChatRes {
        private List<Map<String, Object>> chatHistory;
}

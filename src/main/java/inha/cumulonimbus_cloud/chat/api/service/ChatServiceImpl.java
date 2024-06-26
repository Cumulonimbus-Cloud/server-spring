package inha.cumulonimbus_cloud.chat.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import inha.cumulonimbus_cloud.chat.api.controller.dto.request.PostChatReq;
import inha.cumulonimbus_cloud.chat.api.controller.dto.response.GetChatRes;
import inha.cumulonimbus_cloud.chat.api.controller.dto.response.PostChatRes;
import inha.cumulonimbus_cloud.chat.domain.Chat;
import inha.cumulonimbus_cloud.chat.domain.repository.ChatJpaRepository;
import inha.cumulonimbus_cloud.common.exceptions.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static inha.cumulonimbus_cloud.common.code.status.ErrorStatus.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ChatServiceImpl implements ChatService {

    @Value("${flask.url}")
    private String flaskServerUrl;

    private final ChatJpaRepository chatJpaRepository;

    @Override
    public PostChatRes getAnswer(Long userId, PostChatReq postChatReq) {
        Chat chat = chatJpaRepository.findById(postChatReq.getChatId()).orElseThrow(() -> new BaseException(NOT_EXIST_CHAT));
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String json;
        if(postChatReq.getChatId().equals("6") && postChatReq.getEtc() == null) {
            throw new BaseException(INVALID_CHAT);
        }
        try {
            Map<String, Object> requestBody = new HashMap<>();
            if(postChatReq.getChatId().equals("6") && postChatReq.getEtc() != null) {
                requestBody.put("question", postChatReq.getEtc());
            }
            else {
                requestBody.put("question", chat.getQuestion());
            }
            requestBody.put("userId", userId);  // 사용자 ID 추가
            requestBody.put("chatId", postChatReq.getChatId());
            requestBody.put("etc", postChatReq.getEtc());
            json = new ObjectMapper().writeValueAsString(requestBody);
        } catch (JsonProcessingException e) {
            throw new BaseException(JSON_CONVERT_ERROR);
        }
        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        ResponseEntity<String> response = restTemplate.exchange(flaskServerUrl + "/ask", HttpMethod.POST, entity, String.class);
        // Flask 서버의 응답을 JSON 객체로 처리
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> responseMap;
        try {
            responseMap = objectMapper.readValue(responseBody, Map.class);
        } catch (JsonProcessingException e) {
            throw new BaseException(JSON_CONVERT_ERROR);
        }
        // Flask 서버의 응답 반환
        return PostChatRes.builder().answer((String) responseMap.get("response")).build();
    }

    @Override
    public GetChatRes getChatHistory(Long userId) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // GET 요청을 보낼 URL 생성
        ResponseEntity<String> response = restTemplate.exchange(flaskServerUrl + "/chat_history?userId=" + userId, HttpMethod.GET, null, String.class);
        // Flask 서버의 응답을 JSON 객체로 처리
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> responseMap;
        try {
            responseMap = objectMapper.readValue(responseBody, Map.class);
        } catch (JsonProcessingException e) {
            throw new BaseException(JSON_CONVERT_ERROR);
        }
        // Flask 서버의 응답 반환
        return GetChatRes.builder()
                .chatHistory((List<Map<String, Object>>) responseMap.get("chat_history"))
                .build();
    }
}
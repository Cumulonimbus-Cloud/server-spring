package inha.cumulonimbus_cloud.api.service.auth.social.kakao;


import inha.cumulonimbus_cloud.api.controller.auth.dto.response.GetKakaoRes;

public interface KakaoLoginService {

    String getAccessToken(String authorizationCode);
    GetKakaoRes getUserInfo(String accessToken);
}

package inha.cumulonimbus_cloud.auth.api.service.social.kakao;


import inha.cumulonimbus_cloud.auth.api.controller.dto.response.GetKakaoRes;

public interface KakaoLoginService {

    String getAccessToken(String authorizationCode);
    GetKakaoRes getUserInfo(String accessToken);
}

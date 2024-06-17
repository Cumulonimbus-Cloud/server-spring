package inha.cumulonimbus_cloud.auth.api.service;


import inha.cumulonimbus_cloud.auth.api.controller.dto.response.PostSocialRes;
import inha.cumulonimbus_cloud.user.domain.enums.SocialType;

public interface AuthService {

    PostSocialRes socialLogin(SocialType socialType, String authorizationCode);


}

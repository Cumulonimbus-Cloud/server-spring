package inha.cumulonimbus_cloud.api.service.auth;


import inha.cumulonimbus_cloud.api.controller.auth.dto.response.PostSocialRes;
import inha.cumulonimbus_cloud.domain.user.enums.SocialType;

public interface AuthService {

    PostSocialRes socialLogin(SocialType socialType, String authorizationCode);


}

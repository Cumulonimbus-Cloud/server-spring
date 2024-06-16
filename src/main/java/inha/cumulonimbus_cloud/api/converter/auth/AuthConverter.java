package inha.cumulonimbus_cloud.api.converter.auth;

import inha.cumulonimbus_cloud.api.controller.auth.dto.response.GetKakaoRes;
import inha.cumulonimbus_cloud.api.controller.auth.dto.response.PostSocialRes;
import inha.cumulonimbus_cloud.domain.user.User;
import inha.cumulonimbus_cloud.domain.user.enums.SocialType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static inha.cumulonimbus_cloud.domain.user.enums.Role.USER;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthConverter {

    public static User toUser(GetKakaoRes getKakaoRes){
        return User.builder()
                .username(getKakaoRes.getId())
                .socialType(SocialType.KAKAO)
                .role(USER)
                .build();
    }
    public static PostSocialRes toPostSocialRes(User user, String accessToken){
        return PostSocialRes.builder()
                .id(user.getId())
                .accessToken(accessToken)
                .isFinished(user.isFinished())
                .build();

    }
}

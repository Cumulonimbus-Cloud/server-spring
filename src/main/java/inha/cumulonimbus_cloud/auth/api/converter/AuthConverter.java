package inha.cumulonimbus_cloud.auth.api.converter;

import inha.cumulonimbus_cloud.auth.api.controller.dto.response.GetKakaoRes;
import inha.cumulonimbus_cloud.auth.api.controller.dto.response.PostSocialRes;
import inha.cumulonimbus_cloud.user.domain.User;
import inha.cumulonimbus_cloud.user.domain.enums.SocialType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static inha.cumulonimbus_cloud.user.domain.enums.Role.USER;


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
                .hasGradeCard(user.isHasGradeCard())
                .build();

    }
}

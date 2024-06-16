package inha.cumulonimbus_cloud.api.service.auth;

import inha.cumulonimbus_cloud.api.controller.auth.dto.response.GetKakaoRes;
import inha.cumulonimbus_cloud.api.controller.auth.dto.response.PostSocialRes;
import inha.cumulonimbus_cloud.api.converter.auth.AuthConverter;
import inha.cumulonimbus_cloud.api.service.auth.social.kakao.KakaoLoginService;
import inha.cumulonimbus_cloud.common.exceptions.BaseException;
import inha.cumulonimbus_cloud.domain.user.User;
import inha.cumulonimbus_cloud.domain.user.enums.SocialType;
import inha.cumulonimbus_cloud.domain.user.repository.UserJpaRepository;
import inha.cumulonimbus_cloud.utils.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static inha.cumulonimbus_cloud.common.BaseEntity.State.ACTIVE;
import static inha.cumulonimbus_cloud.common.code.status.ErrorStatus.INVALID_OAUTH_TYPE;
import static inha.cumulonimbus_cloud.common.code.status.ErrorStatus.NOT_FIND_USER;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {

    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final KakaoLoginService kakaoLoginService;



    @Override
    @Transactional
    public PostSocialRes socialLogin(SocialType socialType, String authorizationCode) {
        switch (socialType){
            case KAKAO: {
                GetKakaoRes getKakaoRes = kakaoLoginService.getUserInfo(kakaoLoginService.getAccessToken(authorizationCode));
                boolean isRegistered = userJpaRepository.existsByUsernameAndSocialTypeAndState(getKakaoRes.getId(), SocialType.KAKAO, ACTIVE);
                if (!isRegistered) {
                    User user = AuthConverter.toUser(getKakaoRes);
                    userJpaRepository.save(user);
                }
                User user = userJpaRepository.findByUsernameAndState(getKakaoRes.getId(), ACTIVE)
                        .orElseThrow(() -> new BaseException(NOT_FIND_USER));
                String accessToken = jwtProvider.generateToken(user);
                String refreshToken = jwtProvider.generateRefreshToken(user);
                return AuthConverter.toPostSocialRes(user, accessToken);
            }
            case GOOGLE: {
                //TODO 구글 로그인
            }
            case NAVER: {
                //TODO 네이버 로그인
            }
            default:{
                throw new BaseException(INVALID_OAUTH_TYPE);
            }

        }
    }
}


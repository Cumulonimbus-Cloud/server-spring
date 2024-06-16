package inha.cumulonimbus_cloud.common.code.status;

import inha.cumulonimbus_cloud.common.code.BaseCode;
import inha.cumulonimbus_cloud.common.code.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {

    // 일반적인 응답
    OK(HttpStatus.OK, "COMMON2000", "성공입니다."),

    OAUTH_OK(HttpStatus.OK, "USER2003", "소셜 로그인 성공");
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}
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

    OAUTH_OK(HttpStatus.OK, "USER2003", "소셜 로그인 성공"),
    GET_CHAT_HISTORY_OK(HttpStatus.OK, "CHAT2000", "챗봇 대화 내역 조회 성공"),
    POST_CHAT_OK(HttpStatus.OK, "CHAT2001", "챗봇 대화 성공"),
    POST_GRADE_OK(HttpStatus.OK, "GRADE2000", "성적표 크롤링 성공"),
    PATCH_GRADE_OK(HttpStatus.OK, "GRADE2000", "성적표 크롤링 수정 성공");
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
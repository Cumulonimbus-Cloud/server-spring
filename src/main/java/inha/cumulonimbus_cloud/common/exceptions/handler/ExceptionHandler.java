package inha.cumulonimbus_cloud.common.exceptions.handler;


import inha.cumulonimbus_cloud.common.code.BaseErrorCode;
import inha.cumulonimbus_cloud.common.exceptions.BaseException;

public class ExceptionHandler extends BaseException {
    public ExceptionHandler(BaseErrorCode errorCode) {super(errorCode);}
}

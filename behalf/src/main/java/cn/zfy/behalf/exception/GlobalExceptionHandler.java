package cn.zfy.behalf.exception;

import cn.zfy.behalf.entity.response.base.BaseResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理器
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseResponse methodArgumentNotValidHandler(Exception exception) {
        String message = StringUtils.isEmpty(exception.getMessage()) ? exception.toString() : exception.getMessage();
        return BaseResponse.error(message);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        // 1.校验
        Boolean fieldErrorUnobtainable = (e == null || e.getBindingResult() == null
                || CollectionUtils.isEmpty(e.getBindingResult().getAllErrors()) || e.getBindingResult().getAllErrors().get(0) == null);
        if (Boolean.TRUE.equals(fieldErrorUnobtainable)) {
            return BaseResponse.ok();
        }

        // 2.错误信息
        FieldError fieldError = (FieldError) e.getBindingResult().getAllErrors().get(0);
        String defaultMessage = fieldError.getDefaultMessage();
        // 3.return
        return BaseResponse.error(defaultMessage);
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public BaseResponse unauthorizedException(UnauthorizedException e) {
        return BaseResponse.unauthorized(e.getMessage());
    }
}

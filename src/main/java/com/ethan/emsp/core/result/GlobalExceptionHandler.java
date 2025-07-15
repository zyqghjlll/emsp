package com.ethan.emsp.core.result;

import com.ethan.emsp.core.result.exception.BaseException;
import com.ethan.emsp.core.result.exception.ExternalSystemException;
import com.ethan.emsp.core.result.exception.PersistenceException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ResultMessage<String> handleThrowable(Throwable ex) {
        log.error("Throwable: traceId={}", MDC.get("traceId"), ex);
        String detail = "traceId:" + MDC.get("traceId");
        return ResultMessage.failure(ResultCode.UNEXPECTED_ERROR, detail);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultMessage<Void> handleValidationException(MethodArgumentNotValidException e) {
        // 提取错误信息
        StringBuilder sb = new StringBuilder();
        e.getBindingResult().getFieldErrors().forEach(err ->
                sb.append(err.getField()).append(": ").append(err.getDefaultMessage()).append("; ")
        );
        return ResultMessage.failure(ResultCode.INVALID_ARGUMENT, sb.toString());
    }

    @ExceptionHandler(PersistenceException.class)
    public ResultMessage<Void> handlePersistenceException(PersistenceException ex) {
        log.error("PersistenceException: traceId={}, detail={}", MDC.get("traceId"), ex.getMessage(), ex);
        return ResultMessage.failure(ex.getErrorCode(), ex.getMessage());
    }

    @ExceptionHandler(ExternalSystemException.class)
    public ResultMessage<Void> handleExternalSystemException(ExternalSystemException ex) {
        log.error("ExternalSystemException: traceId={}, detail={}", MDC.get("traceId"), ex.getMessage(), ex);
        return ResultMessage.failure(ex.getErrorCode(), ex.getMessage());
    }

    @ExceptionHandler(BaseException.class)
    public ResultMessage<Void> handleBaseException(BaseException ex) {
        log.error("BaseException: type={}, traceId={}, code={}, detail={}",
                ex.getClass().getSimpleName(),
                MDC.get("traceId"),
                ex.getErrorCode().getCode(),
                ex.getMessage(), ex);

        return ResultMessage.failure(ex.getErrorCode(), ex.getMessage());
    }
}

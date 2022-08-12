package cn.zfy.behalf.exception;

/**
 * @Classname CustomException
 * @Description 自定义运行时异常
 */
public class CustomException extends RuntimeException {
    public CustomException() {
        super();
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }
}

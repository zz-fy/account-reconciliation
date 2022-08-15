package cn.zfy.behalf.entity.response.base;

import lombok.Data;

/**
 * 基本响应 实体类
 */
@Data
public class BaseResponse<T> {

    /**
     * 提示消息
     */
    private String message;

    /**
     * 状态码（注：-1：权限不足；0：失败；1：成功）
     */
    private int code;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 是否成功
     */
    private boolean success;


    /**
     * 完成
     *
     * @return 响应
     */
    public static BaseResponse ok() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setSuccess(true);
        baseResponse.setCode(1);
        return baseResponse;
    }

    /**
     * 完成
     *
     * @param message 消息
     * @return 响应
     */
    public static BaseResponse ok(String message) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(1);
        baseResponse.setSuccess(true);
        baseResponse.setMessage(message);
        return baseResponse;
    }

    /**
     * 完成
     *
     * @param message 消息
     * @param data    数据
     * @return 响应
     */
    public static <T> BaseResponse ok(String message, T data) {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setCode(1);
        baseResponse.setMessage(message);
        baseResponse.setSuccess(true);
        baseResponse.setData(data);
        return baseResponse;
    }

    /**
     * 完成
     *
     * @param data 数据
     * @return 响应
     */
    public static <T> BaseResponse ok(T data) {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setCode(1);
        baseResponse.setData(data);
        baseResponse.setSuccess(true);
        return baseResponse;
    }

    /**
     * 错误
     *
     * @param message 消息
     * @return 响应
     */
    public static BaseResponse error(String message) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(0);
        baseResponse.setSuccess(false);
        baseResponse.setMessage(message);
        return baseResponse;
    }

    /**
     * 错误
     *
     * @param message 消息
     * @param data    数据
     * @param <T>
     * @return 响应
     */
    public static <T> BaseResponse error(String message, T data) {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setCode(0);
        baseResponse.setMessage(message);
        baseResponse.setSuccess(false);
        baseResponse.setData(data);
        return baseResponse;
    }

    /**
     * 未授权
     *
     * @param message 消息
     * @return 响应
     */
    public static BaseResponse unauthorized(String message) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(-1);
        baseResponse.setSuccess(false);
        baseResponse.setMessage(message);
        return baseResponse;
    }
}

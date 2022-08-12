package cn.zfy.behalf.constants;

/**
 * @Classname AppConstants
 * @Description 常量
 */
public class AppConstants {

    private AppConstants() {
    }


    /**
     * 默认密码
     */
    public static final String DEFAULT_PWD = "123456";


    /**
     * 请求头所带token名字
     */
    public static final String REQUEST_HEAD_TOKEN = "token";

    /**
     * token 过期分钟数
     */
    public static final Integer TOKEN_EXPIRE_MINUTE = 60;


    /**
     * 存入redis的用户jwt信息 key
     *
     * @param userId
     * @return
     */
    public static String getUserTokenKey(Long userId) {
        return "behalf:user_id_token:".concat(String.valueOf(userId)).concat(":token");
    }

}

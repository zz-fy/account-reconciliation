package cn.zfy.behalf.entity.response.auth;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @Classname UserInfoResponse
 * @Description 用户个人信息
 */
@Data
@Builder
public class UserInfoResponse {

    private Long userId;

    private String username;

    private String name;

    private String avatar;

    private List<String> roles;

}

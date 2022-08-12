package cn.zfy.behalf.config.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @Classname UserAuthInfo
 * @Description 用户认证存放信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAuthInfo {

    private Long id;

    private String username;

    private String name;

}

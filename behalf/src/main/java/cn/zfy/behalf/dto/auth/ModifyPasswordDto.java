package cn.zfy.behalf.dto.auth;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;


/**
 * @Classname ModifyPasswordRequest
 * @Description 修改密码
 */
@Data
public class ModifyPasswordDto {


    @NotBlank(message = "旧密码不能为空")
    private String oldPassword;

    @NotBlank(message = "新密码不能为空")
    private String newPassword;

}

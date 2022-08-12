package cn.zfy.behalf.dto.biz;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class UserInsertDto {

    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotBlank(message = "用户名不能为空")
    private String username;

    private String phone;

    private String password;
}

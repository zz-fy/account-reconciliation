package cn.zfy.behalf.dto.biz;

import lombok.Data;

import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SoldAccountModifyDto {


    @NotNull(message = "id信息不能为空")
    private Long id;

    @NotBlank(message = "售出账户名称不能为空")
    private String name;

    @NotBlank(message = "售出账户电话名称不能为空")
    private String phone;

}

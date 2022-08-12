package cn.zfy.behalf.dto.biz;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;


@Data
public class SoldAccountInsertDto {


    @NotBlank(message = "售出账户名称不能为空")
    private String name;

    @NotBlank(message = "售出账户电话名称不能为空")
    private String phone;

}

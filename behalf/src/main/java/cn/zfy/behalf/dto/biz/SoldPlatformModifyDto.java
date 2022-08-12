package cn.zfy.behalf.dto.biz;

import lombok.Data;

import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SoldPlatformModifyDto {


    @NotNull(message = "id信息不能为空")
    private Long id;

    @NotBlank(message = "售出平台名称不能为空")
    private String name;

}

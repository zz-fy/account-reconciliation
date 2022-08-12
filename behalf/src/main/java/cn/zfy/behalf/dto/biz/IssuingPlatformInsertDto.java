package cn.zfy.behalf.dto.biz;

import lombok.Data;

import org.hibernate.validator.constraints.NotBlank;

@Data
public class IssuingPlatformInsertDto {


    @NotBlank(message = "代发平台名称不能为空")
    private String name;

}

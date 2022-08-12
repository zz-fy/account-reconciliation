package cn.zfy.behalf.dto.biz;

import lombok.Data;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
public class IssuingCompanyInsertDto {


    @NotNull(message = "代发平台不能为空")
    private Long issuingPlatformId;

    @NotBlank(message = "代发公司名称不能为空")
    private String name;

}

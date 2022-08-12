package cn.zfy.behalf.dto.biz;

import lombok.Data;

@Data
public class ProductModifyDto {

    private Long id;

    private String name;

    private Long issuingCompanyId;

    private String issuingUrl;

    private Boolean enabled;

}

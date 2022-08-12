package cn.zfy.behalf.dto.biz;

import lombok.Data;

@Data
public class ProductInsertDto {

    private String name;

    private Long issuingCompanyId;

    private String issuingUrl;

}

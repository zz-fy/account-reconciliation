package cn.zfy.behalf.dto.biz;

import cn.zfy.behalf.dto.base.PageRequest;
import lombok.Data;

@Data
public class ProductPageSearchDto extends PageRequest {

    private String name;

    private Long issuingCompanyId;

    private Long issuingPlatformId;

}

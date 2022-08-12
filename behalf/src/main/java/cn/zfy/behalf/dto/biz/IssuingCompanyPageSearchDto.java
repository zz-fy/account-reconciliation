package cn.zfy.behalf.dto.biz;

import cn.zfy.behalf.dto.base.PageRequest;
import lombok.Data;


@Data
public class IssuingCompanyPageSearchDto extends PageRequest {


    private Long issuingPlatformId;

    private String name;

}

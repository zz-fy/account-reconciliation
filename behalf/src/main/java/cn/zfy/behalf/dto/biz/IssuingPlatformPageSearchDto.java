package cn.zfy.behalf.dto.biz;

import cn.zfy.behalf.dto.base.PageRequest;
import lombok.Data;

@Data
public class IssuingPlatformPageSearchDto extends PageRequest {

    private String name;

}

package cn.zfy.behalf.dto.biz;

import cn.zfy.behalf.dto.base.PageRequest;
import lombok.Data;

@Data
public class SoldAccountPageSearchDto extends PageRequest {

    private String name;

    private String phone;

}

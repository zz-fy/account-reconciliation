package cn.zfy.behalf.bo;

import cn.zfy.behalf.entity.po.IssuingCompany;
import cn.zfy.behalf.entity.po.IssuingPlatform;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class IssuingPlatformBo extends IssuingPlatform {


    private List<IssuingCompany> issuingCompanyList = Collections.emptyList();

}

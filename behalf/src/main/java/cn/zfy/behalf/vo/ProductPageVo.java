package cn.zfy.behalf.vo;

import cn.zfy.behalf.entity.po.Product;
import lombok.Data;

@Data
public class ProductPageVo extends Product {

    private String issuingCompanyName;

    private Long issuingPlatformId;

    private String issuingPlatformName;

}

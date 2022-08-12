package cn.zfy.behalf.bo;

import cn.zfy.behalf.entity.po.Order;
import lombok.Data;

/**
 * 订单信息
 */
@Data
public class OrderInfo extends Order {

    /**
     * 产品名称
     */
    private String productName;


    /**
     * 卖出平台
     */
    private String soldPlatformName;

    /**
     * 卖出账户
     */
    private String soldAccountName;

    /**
     * 代发平台
     */
    private Long issuingPlatformId;

    private String issuingPlatformName;

    /**
     * 代发公司
     */
    private Long issuingCompanyId;

    private String issuingCompanyName;

    /**
     * 代发URL
     */
    private String issuingUrl;


    /**
     * 订单客户Id
     */
    private Long orderCustomerId;

    /**
     * 昵称(客户在平台名)
     */
    private String customerNickname;

    /**
     * 客户真名
     */
    private String customerRealName;

    /**
     * 客户电话
     */
    private String customerPhone;

    /**
     * 客户地址
     */
    private String customerAddress;


}

package cn.zfy.behalf.dto.biz;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderModifyDto {

    private Long id;

    /**
     * 下单时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date orderTime;

    /**
     * 产品Id
     */
    private Long productId;

    /**
     * 售出账户id
     */
    private Long soldAccountId;

    /**
     * 售出平台名
     */
    private Long soldPlatformId;

    /**
     * 代发公司名称
     */
    private Long issuingCompanyId;

    /**
     * 标价
     */
    private BigDecimal markPrice;

    /**
     * 售出价格
     */
    private BigDecimal soldPrice;

    /**
     * 成本
     */
    private BigDecimal costPrice;

    /**
     * 售出数量
     */
    private BigDecimal soldQuantity;

    /**
     * 订单状态
     */
    private Integer status;

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

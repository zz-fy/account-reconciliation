package cn.zfy.behalf.entity.po;

import cn.zfy.behalf.entity.po.base.BasePo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单表
 *
 * @author zfy
 * @since 2022-07-10
 */
@Data
@TableName("`order`")
public class Order extends BasePo implements Serializable {


    /**
     * 订单编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 下单时间
     */
    @TableField("order_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date orderTime;

    @TableField("product_id")
    private Long productId;

    /**
     * 售出账户id
     */
    @TableField("sold_account_id")
    private Long soldAccountId;

    /**
     * 售出平台名
     */
    @TableField("sold_platform_id")
    private Long soldPlatformId;

    /**
     * 代发公司名称
     */
    @TableField("issuing_company_id")
    private Long issuingCompanyId;

    /**
     * 标价
     */
    @TableField("mark_price")
    private BigDecimal markPrice;

    /**
     * 售出价格
     */
    @TableField("sold_price")
    private BigDecimal soldPrice;

    /**
     * 成本
     */
    @TableField("cost_price")
    private BigDecimal costPrice;

    /**
     * 售出数量
     */
    @TableField("sold_quantity")
    private BigDecimal soldQuantity;

    /**
     * 订单状态
     */
    @TableField("`status`")
    private Integer status;


}

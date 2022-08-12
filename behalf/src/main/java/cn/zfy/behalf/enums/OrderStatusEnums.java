package cn.zfy.behalf.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单状态 枚举
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnums {


    TO_BE_SHIPPED(0, "待发货"),
    SHIPPED(1, "已发货"),
    SIGNED(2, "已签收"),
    TRADE_SUCCESS(3, "交易成功"),
    REFUND(4, "退款处理");

    private Integer code;

    private String desc;

}

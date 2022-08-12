package cn.zfy.behalf.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 订单客户
 *
 * @author zfy
 * @since 2022-07-10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("order_customer")
public class OrderCustomer implements Serializable {


    @TableId("id")
    private Long id;

    @TableField("order_id")
    private Long orderId;

    /**
     * 昵称(客户在平台名)
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 客户真名
     */
    @TableField("real_name")
    private String realName;

    /**
     * 客户电话
     */
    @TableField("phone")
    private String phone;

    /**
     * 客户地址
     */
    @TableField("address")
    private String address;


}

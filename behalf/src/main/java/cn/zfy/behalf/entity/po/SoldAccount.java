package cn.zfy.behalf.entity.po;

import cn.zfy.behalf.entity.po.base.BasePo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 售出账户
 *
 * @author zfy
 * @since 2022-07-10
 */
@Data
@TableName("sold_account")
public class SoldAccount extends BasePo implements Serializable {


    /**
     * 账户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 账户名
     */
    @TableField("`name`")
    private String name;

    /**
     * 电话
     */
    @TableField("phone")
    private String phone;


}

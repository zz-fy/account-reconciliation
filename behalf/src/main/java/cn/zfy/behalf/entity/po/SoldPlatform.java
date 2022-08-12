package cn.zfy.behalf.entity.po;

import cn.zfy.behalf.entity.po.base.BasePo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 售出平台  闲鱼/转转。。
 *
 * @author zfy
 * @since 2022-07-10
 */
@Data
@TableName("sold_platform")
public class SoldPlatform extends BasePo implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 售出平台名
     */
    @TableField("`name`")
    private String name;


}

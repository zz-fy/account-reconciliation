package cn.zfy.behalf.entity.po;

import cn.zfy.behalf.entity.po.base.BasePo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 代发平台
 *
 * @author zfy
 * @since 2022-07-10
 */
@Data
@TableName("issuing_platform")
public class IssuingPlatform extends BasePo implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 代发平台名
     */
    @TableField("`name`")
    private String name;


}

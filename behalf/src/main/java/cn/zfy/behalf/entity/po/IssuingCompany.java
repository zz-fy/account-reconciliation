package cn.zfy.behalf.entity.po;

import cn.zfy.behalf.entity.po.base.BasePo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 代发公司
 *
 * @author zfy
 * @since 2022-07-10
 */
@Data
@TableName("issuing_company")
public class IssuingCompany extends BasePo implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 代发平台
     */
    @TableField("issuing_platform_id")
    private Long issuingPlatformId;

    /**
     * 公司名
     */
    @TableField("`name`")
    private String name;


}

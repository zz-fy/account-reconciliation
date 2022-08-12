package cn.zfy.behalf.entity.po;

import cn.zfy.behalf.entity.po.base.BasePo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 产品表(由于代发平台价格是变动的，所以没有价格字段)
 *
 * @author zfy
 * @since 2022-07-10
 */
@Data
@TableName("product")
public class Product extends BasePo implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 产品名称(规格参数拼在括号)
     */
    @TableField("name")
    private String name;

    /**
     * 代发公司id
     */
    @TableField("issuing_company_id")
    private Long issuingCompanyId;

    /**
     * 代发链接
     */
    @TableField("issuing_url")
    private String issuingUrl;

    /**
     * 是否可用
     */
    @TableField("`enabled`")
    private Boolean enabled;


}

package cn.zfy.behalf.entity.po;

import cn.zfy.behalf.entity.po.base.BasePo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("`user`")
public class User extends BasePo implements Serializable {


    /**
     * 主键Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 登录名
     */
    @TableField("username")
    private String username;

    /**
     * 手机号
     */
    @TableField("phone")
    private String phone;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    /**
     * 密码
     */
    @TableField("password")
    @JsonIgnore
    private String password;


}

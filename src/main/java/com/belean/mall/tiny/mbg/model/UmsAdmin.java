package com.belean.mall.tiny.mbg.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import org.hibernate.validator.constraints.Length;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
* 后台用户表
* @TableName ums_admin
*/
@Schema(description = "后台用户表")
@TableName(value = "ums_admin")
@Getter
@Setter
@ToString
public class UmsAdmin {


    /**
    * 
    */
    @TableId(value = "id", type = IdType.NONE)
    @Schema(description = "")
    private Long id;

    /**
    * 
    */
    @Schema(description = "")
    @Length(max= 64,message="编码长度不能超过64")
    private String username;

    /**
    * 
    */
    @Schema(description = "")
    @Length(max= 64,message="编码长度不能超过64")
    private String password;

    /**
    * 头像
    */
    @Schema(description = "头像")
    @Length(max= 500,message="编码长度不能超过500")
    private String icon;

    /**
    * 邮箱
    */
    @Schema(description = "邮箱")
    @Length(max= 100,message="编码长度不能超过100")
    private String email;

    /**
    * 昵称
    */
    @Schema(description = "昵称")
    @Length(max= 200,message="编码长度不能超过200")
    private String nickName;

    /**
    * 备注信息
    */
    @Schema(description = "备注信息")
    @Length(max= 500,message="编码长度不能超过500")
    private String note;

    /**
    * 创建时间
    */
    @Schema(description = "创建时间")
    private Date createTime;

    /**
    * 最后登录时间
    */
    @Schema(description = "最后登录时间")
    private Date loginTime;

    /**
    * 帐号启用状态：0->禁用；1->启用
    */
    @Schema(description = "帐号启用状态：0->禁用；1->启用")
    private Integer status;
}

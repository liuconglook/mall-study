package com.belean.mall.tiny.mbg.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import org.hibernate.validator.constraints.Length;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
* 后台用户登录日志表
* @TableName ums_admin_login_log
*/
@Schema(description = "后台用户登录日志表")
@TableName(value = "ums_admin_login_log")
@Getter
@Setter
@ToString
public class UmsAdminLoginLog {


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
    private Long adminId;

    /**
    * 
    */
    @Schema(description = "")
    private Date createTime;

    /**
    * 
    */
    @Schema(description = "")
    @Length(max= 64,message="编码长度不能超过64")
    private String ip;

    /**
    * 
    */
    @Schema(description = "")
    @Length(max= 100,message="编码长度不能超过100")
    private String address;

    /**
    * 浏览器登录类型
    */
    @Schema(description = "浏览器登录类型")
    @Length(max= 100,message="编码长度不能超过100")
    private String userAgent;
}

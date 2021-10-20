package com.belean.mall.tiny.mbg.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import org.hibernate.validator.constraints.Length;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
* 后台用户角色表
* @TableName ums_role
*/
@Schema(description = "后台用户角色表")
@TableName(value = "ums_role")
@Getter
@Setter
@ToString
public class UmsRole {


    /**
    * 
    */
    @TableId(value = "id", type = IdType.NONE)
    @Schema(description = "")
    private Long id;

    /**
    * 名称
    */
    @Schema(description = "名称")
    @Length(max= 100,message="编码长度不能超过100")
    private String name;

    /**
    * 描述
    */
    @Schema(description = "描述")
    @Length(max= 500,message="编码长度不能超过500")
    private String description;

    /**
    * 后台用户数量
    */
    @Schema(description = "后台用户数量")
    private Integer adminCount;

    /**
    * 创建时间
    */
    @Schema(description = "创建时间")
    private Date createTime;

    /**
    * 启用状态：0->禁用；1->启用
    */
    @Schema(description = "启用状态：0->禁用；1->启用")
    private Integer status;

    /**
    * 
    */
    @Schema(description = "")
    private Integer sort;
}

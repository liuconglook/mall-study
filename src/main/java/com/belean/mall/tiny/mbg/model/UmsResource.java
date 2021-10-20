package com.belean.mall.tiny.mbg.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import org.hibernate.validator.constraints.Length;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
* 后台资源表
* @TableName ums_resource
*/
@Schema(description = "后台资源表")
@TableName(value = "ums_resource")
@Getter
@Setter
@ToString
public class UmsResource {


    /**
    * 
    */
    @TableId(value = "id", type = IdType.NONE)
    @Schema(description = "")
    private Long id;

    /**
    * 创建时间
    */
    @Schema(description = "创建时间")
    private Date createTime;

    /**
    * 资源名称
    */
    @Schema(description = "资源名称")
    @Length(max= 200,message="编码长度不能超过200")
    private String name;

    /**
    * 资源URL
    */
    @Schema(description = "资源URL")
    @Length(max= 200,message="编码长度不能超过200")
    private String url;

    /**
    * 描述
    */
    @Schema(description = "描述")
    @Length(max= 500,message="编码长度不能超过500")
    private String description;

    /**
    * 资源分类ID
    */
    @Schema(description = "资源分类ID")
    private Long categoryId;
}

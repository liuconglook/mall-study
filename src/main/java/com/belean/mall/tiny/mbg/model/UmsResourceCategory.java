package com.belean.mall.tiny.mbg.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import org.hibernate.validator.constraints.Length;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
* 资源分类表
* @TableName ums_resource_category
*/
@Schema(description = "资源分类表")
@TableName(value = "ums_resource_category")
@Getter
@Setter
@ToString
public class UmsResourceCategory {


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
    * 分类名称
    */
    @Schema(description = "分类名称")
    @Length(max= 200,message="编码长度不能超过200")
    private String name;

    /**
    * 排序
    */
    @Schema(description = "排序")
    private Integer sort;
}

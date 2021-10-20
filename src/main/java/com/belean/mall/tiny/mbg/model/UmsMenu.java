package com.belean.mall.tiny.mbg.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import org.hibernate.validator.constraints.Length;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
* 后台菜单表
* @TableName ums_menu
*/
@Schema(description = "后台菜单表")
@TableName(value = "ums_menu")
@Getter
@Setter
@ToString
public class UmsMenu {


    /**
    * 
    */
    @TableId(value = "id", type = IdType.NONE)
    @Schema(description = "")
    private Long id;

    /**
    * 父级ID
    */
    @Schema(description = "父级ID")
    private Long parentId;

    /**
    * 创建时间
    */
    @Schema(description = "创建时间")
    private Date createTime;

    /**
    * 菜单名称
    */
    @Schema(description = "菜单名称")
    @Length(max= 100,message="编码长度不能超过100")
    private String title;

    /**
    * 菜单级数
    */
    @Schema(description = "菜单级数")
    private Integer level;

    /**
    * 菜单排序
    */
    @Schema(description = "菜单排序")
    private Integer sort;

    /**
    * 前端名称
    */
    @Schema(description = "前端名称")
    @Length(max= 100,message="编码长度不能超过100")
    private String name;

    /**
    * 前端图标
    */
    @Schema(description = "前端图标")
    @Length(max= 200,message="编码长度不能超过200")
    private String icon;

    /**
    * 前端隐藏
    */
    @Schema(description = "前端隐藏")
    private Integer hidden;
}

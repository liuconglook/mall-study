package com.belean.mall.tiny.mbg.model;

import com.baomidou.mybatisplus.annotation.IdType;
import org.hibernate.validator.constraints.Length;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
* 后台角色菜单关系表
* @TableName ums_role_menu_relation
*/
@Schema(description = "后台角色菜单关系表")
@TableName(value = "ums_role_menu_relation")
@Getter
@Setter
@ToString
public class UmsRoleMenuRelation {


    /**
    * 
    */
    @TableId(value = "id", type = IdType.NONE)
    @Schema(description = "")
    private Long id;

    /**
    * 角色ID
    */
    @Schema(description = "角色ID")
    private Long roleId;

    /**
    * 菜单ID
    */
    @Schema(description = "菜单ID")
    private Long menuId;
}

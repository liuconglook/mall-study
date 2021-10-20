package com.belean.mall.tiny.mbg.model;

import com.baomidou.mybatisplus.annotation.IdType;
import org.hibernate.validator.constraints.Length;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
* 后台用户角色和权限关系表
* @TableName ums_role_permission_relation
*/
@Schema(description = "后台用户角色和权限关系表")
@TableName(value = "ums_role_permission_relation")
@Getter
@Setter
@ToString
public class UmsRolePermissionRelation {


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
    private Long roleId;

    /**
    * 
    */
    @Schema(description = "")
    private Long permissionId;
}

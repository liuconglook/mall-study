package com.belean.mall.tiny.mbg.model;

import com.baomidou.mybatisplus.annotation.IdType;
import org.hibernate.validator.constraints.Length;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
* 后台角色资源关系表
* @TableName ums_role_resource_relation
*/
@Schema(description = "后台角色资源关系表")
@TableName(value = "ums_role_resource_relation")
@Getter
@Setter
@ToString
public class UmsRoleResourceRelation {


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
    * 资源ID
    */
    @Schema(description = "资源ID")
    private Long resourceId;
}

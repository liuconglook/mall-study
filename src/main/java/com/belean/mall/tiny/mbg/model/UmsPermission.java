package com.belean.mall.tiny.mbg.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import org.hibernate.validator.constraints.Length;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
* 后台用户权限表
* @TableName ums_permission
*/
@Schema(description = "后台用户权限表")
@TableName(value = "ums_permission")
@Getter
@Setter
@ToString
public class UmsPermission {


    /**
    * 
    */
    @TableId(value = "id", type = IdType.NONE)
    @Schema(description = "")
    private Long id;

    /**
    * 父级权限id
    */
    @Schema(description = "父级权限id")
    private Long pid;

    /**
    * 名称
    */
    @Schema(description = "名称")
    @Length(max= 100,message="编码长度不能超过100")
    private String name;

    /**
    * 权限值
    */
    @Schema(description = "权限值")
    @Length(max= 200,message="编码长度不能超过200")
    private String value;

    /**
    * 图标
    */
    @Schema(description = "图标")
    @Length(max= 500,message="编码长度不能超过500")
    private String icon;

    /**
    * 权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）
    */
    @Schema(description = "权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）")
    private Integer type;

    /**
    * 前端资源路径
    */
    @Schema(description = "前端资源路径")
    @Length(max= 200,message="编码长度不能超过200")
    private String uri;

    /**
    * 启用状态；0->禁用；1->启用
    */
    @Schema(description = "启用状态；0->禁用；1->启用")
    private Integer status;

    /**
    * 创建时间
    */
    @Schema(description = "创建时间")
    private Date createTime;

    /**
    * 排序
    */
    @Schema(description = "排序")
    private Integer sort;
}

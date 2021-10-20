package com.belean.mall.tiny.dto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * 用户登录参数
 */
@Schema(description = "用户登录参数")
@Getter
@Setter
public class UmsAdminLoginParam {
    @Schema(description = "用户名")
    @NotEmpty(message = "用户名不能为空")
    private String username;

    @Schema(description = "密码")
    @NotEmpty(message = "密码不能为空")
    private String password;
}
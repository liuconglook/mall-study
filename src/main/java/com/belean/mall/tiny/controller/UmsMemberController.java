package com.belean.mall.tiny.controller;

import com.belean.mall.tiny.common.api.CommonResult;
import com.belean.mall.tiny.service.UmsMemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 会员登录注册管理Controller
 */
@RestController
@Tag(name = "会员登录注册管理", description = "获取、校验验证码")
@RequestMapping("/sso")
public class UmsMemberController {
    @Autowired
    private UmsMemberService memberService;

    @Operation(tags = {"会员登录注册管理"}, summary = "获取验证码")
    @GetMapping(value = "/getAuthCode")
    public CommonResult getAuthCode(@RequestParam String telephone) {
        return memberService.generateAuthCode(telephone);
    }

    @Operation(tags = {"会员登录注册管理"}, summary = "判断验证码是否正确")
    @PostMapping(value = "/verifyAuthCode")
    public CommonResult updatePassword(@RequestParam String telephone, @RequestParam String authCode) {
        return memberService.verifyAuthCode(telephone,authCode);
    }
}
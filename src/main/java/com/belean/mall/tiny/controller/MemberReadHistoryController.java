package com.belean.mall.tiny.controller;

import com.belean.mall.tiny.common.api.CommonResult;
import com.belean.mall.tiny.nosql.mongodb.document.MemberReadHistory;
import com.belean.mall.tiny.service.MemberReadHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员商品浏览记录管理Controller
 */
@RestController
@Tag(name = "会员商品浏览记录管理", description = "")
@RequestMapping("/member/readHistory")
public class MemberReadHistoryController {

    @Autowired
    private MemberReadHistoryService memberReadHistoryService;

    @Operation(tags = {"会员商品浏览记录管理"}, summary = "创建浏览记录")
    @PostMapping(value = "/create")
    public CommonResult create(@RequestBody MemberReadHistory memberReadHistory) {
        int count = memberReadHistoryService.create(memberReadHistory);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @Operation(tags = {"会员商品浏览记录管理"}, summary = "删除浏览记录")
    @PostMapping(value = "/delete")
    public CommonResult delete(@RequestParam("ids") List<String> ids) {
        int count = memberReadHistoryService.delete(ids);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @Operation(tags = {"会员商品浏览记录管理"}, summary = "展示浏览记录")
    @GetMapping(value = "/list")
    public CommonResult<List<MemberReadHistory>> list(Long memberId) {
        List<MemberReadHistory> memberReadHistoryList = memberReadHistoryService.list(memberId);
        return CommonResult.success(memberReadHistoryList);
    }
}
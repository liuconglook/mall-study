package com.belean.mall.tiny.controller;

import com.belean.mall.tiny.common.api.CommonPage;
import com.belean.mall.tiny.common.api.CommonResult;
import com.belean.mall.tiny.nosql.elasticsearch.document.EsProduct;
import com.belean.mall.tiny.service.EsProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 搜索商品管理Controller
 */
@RestController
@Tag(name = "搜索商品管理", description = "从数据库导入、搜索、创建、删除、批量删除ES商品")
@RequestMapping("/esProduct")
public class EsProductController {
    @Autowired
    private EsProductService esProductService;

    @Operation(tags = {"搜索商品管理"}, summary = "导入所有数据库中商品到ES")
    @PostMapping(value = "/importAll")
    public CommonResult<Integer> importAllList() {
        int count = esProductService.importAll();
        return CommonResult.success(count);
    }

    @Operation(tags = {"搜索商品管理"}, summary = "根据id删除商品")
    @GetMapping(value = "/delete/{id}")
    public CommonResult<Object> delete(@PathVariable Long id) {
        esProductService.delete(id);
        return CommonResult.success(null);
    }

    @Operation(tags = {"搜索商品管理"}, summary = "根据id批量删除商品")
    @PostMapping(value = "/delete/batch")
    public CommonResult<Object> delete(@RequestParam("ids") List<Long> ids) {
        esProductService.delete(ids);
        return CommonResult.success(null);
    }

    @Operation(tags = {"搜索商品管理"}, summary = "根据id创建商品")
    @PostMapping(value = "/create/{id}")
    public CommonResult<EsProduct> create(@PathVariable Long id) {
        EsProduct esProduct = esProductService.create(id);
        if (esProduct != null) {
            return CommonResult.success(esProduct);
        } else {
            return CommonResult.failed();
        }
    }

    @Operation(tags = {"搜索商品管理"}, summary = "简单搜索")
    @GetMapping(value = "/search/simple")
    public CommonResult<CommonPage<EsProduct>> search(@RequestParam(required = false) String keyword,
                                                      @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                      @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        Page<EsProduct> esProductPage = esProductService.search(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(esProductPage));
    }

}
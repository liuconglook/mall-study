package com.belean.mall.tiny.controller;

import com.belean.mall.tiny.common.api.CommonPage;
import com.belean.mall.tiny.common.api.CommonResult;
import com.belean.mall.tiny.mbg.model.PmsBrand;
import com.belean.mall.tiny.service.PmsBrandService;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;


/**
 * 品牌管理Controller
 */
@Tag(name = "品牌管理", description = "全查询、分页查、创建；根据Id查询、更新、删除品牌")
@RestController
@RequestMapping("/brand")
@Slf4j
public class PmsBrandController {

    @Autowired
    private PmsBrandService demoService;

    @Operation(tags = {"品牌管理"}, summary = "查询全部")
    @GetMapping("/listAll")
    @PreAuthorize("hasAuthority('pms:brand:read')")
    public CommonResult<List<PmsBrand>> getBrandList() {
        log.info("查询所有");
        return CommonResult.success(demoService.listAllBrand());
    }

    @Operation(tags = {"品牌管理"}, summary = "添加品牌")
    @PostMapping("/create")
    public CommonResult createBrand(@Parameter(description = "品牌实体") @Valid @RequestBody PmsBrand pmsBrand, BindingResult result) {
        // 校验
        if(result.hasErrors()) { // 是否有参数异常
            List<FieldError> fieldErrors = result.getFieldErrors();// 获取异常参数集
            fieldErrors.forEach(fieldError -> {
                String field = fieldError.getField(); // 异常参数名
//                Object value = fieldError.getRejectedValue();// 异常参数值
//                boolean bindingFailure = fieldError.isBindingFailure(); // true表示类型不匹配，false表示校验失败
                String message = fieldError.getDefaultMessage();// 异常消息
                log.error("参数：{}，{}", field, message);
            });
            for (FieldError fieldError : fieldErrors) {
                return CommonResult.validateFailed(fieldError.getDefaultMessage());
            }
        }

        CommonResult commonResult;
        int count = demoService.createBrand(pmsBrand);
        if (count == 1) {
            commonResult = CommonResult.success(pmsBrand);
            log.debug("createBrand success:{}", pmsBrand);
        } else {
            commonResult = CommonResult.failed("操作失败");
            log.debug("createBrand failed:{}", pmsBrand);
        }
        return commonResult;
    }

    @Operation(tags = {"品牌管理"}, summary = "根据ID更新")
    @PostMapping("/update/{id}")
    public CommonResult updateBrand(@Parameter(description = "要更新的ID") @PathVariable("id") Long id, @Valid @RequestBody PmsBrand pmsBrandDto, @ApiIgnore BindingResult result) {
        CommonResult commonResult;
        int count = demoService.updateBrand(id, pmsBrandDto);
        if (count == 1) {
            commonResult = CommonResult.success(pmsBrandDto);
            log.debug("updateBrand success:{}", pmsBrandDto);
        } else {
            commonResult = CommonResult.failed("操作失败");
            log.debug("updateBrand failed:{}", pmsBrandDto);
        }
        return commonResult;
    }

    @Operation(tags = {"品牌管理"}, summary = "根据ID删除")
    @GetMapping("/delete/{id}")
    public CommonResult deleteBrand(@Parameter(description = "ID") @PathVariable("id") Long id) {
        int count = demoService.deleteBrand(id);
        if (count == 1) {
            log.debug("deleteBrand success :id={}", id);
            return CommonResult.success(null);
        } else {
            log.debug("deleteBrand failed :id={}", id);
            return CommonResult.failed("操作失败");
        }
    }

    @Operation(tags = {"品牌管理"}, summary = "分页查询")
    @Parameters({
            @Parameter(name = "pageNum", description = "页码", example = "1"),
            @Parameter(name = "pageSize", description = "页数", example = "3")
    })
    @GetMapping("/list")
    public CommonResult<CommonPage<PmsBrand>> listBrand(@Parameter(description = "页码") @RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum,
                                                        @Parameter(description = "页数") @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {
        List<PmsBrand> brandList = demoService.listBrand(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(brandList));
    }

    @Operation(tags = {"品牌管理"}, summary = "根据ID查询")
    @GetMapping("/{id}")
    public CommonResult<PmsBrand> brand(@Parameter(description = "ID") @PathVariable("id") Long id) {
        return CommonResult.success(demoService.getBrand(id));
    }
}

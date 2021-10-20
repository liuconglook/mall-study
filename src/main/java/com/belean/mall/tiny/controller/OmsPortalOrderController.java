package com.belean.mall.tiny.controller;

import com.belean.mall.tiny.dto.OrderParam;
import com.belean.mall.tiny.service.OmsPortalOrderService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 订单管理Controller
 */
@RestController
@Tag(name = "订单管理")
@RequestMapping("/order")
public class OmsPortalOrderController {
    @Autowired
    private OmsPortalOrderService portalOrderService;

    @Operation(tags = {"订单管理"}, summary = "根据购物车信息生成订单")
    @PostMapping("/generateOrder")
    public Object generateOrder(@RequestBody OrderParam orderParam) {
        return portalOrderService.generateOrder(orderParam);
    }
}
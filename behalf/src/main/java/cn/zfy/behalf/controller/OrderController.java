package cn.zfy.behalf.controller;

import cn.zfy.behalf.dto.biz.OrderInsertDto;
import cn.zfy.behalf.dto.biz.OrderModifyDto;
import cn.zfy.behalf.dto.biz.OrderSearchDto;
import cn.zfy.behalf.entity.response.base.BaseResponse;
import cn.zfy.behalf.entity.response.base.PageList;
import cn.zfy.behalf.service.OrderService;
import cn.zfy.behalf.bo.OrderInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 订单服务
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/exist-check")
    public BaseResponse<OrderInfo> checkOrderExist(
            @RequestParam(value = "productId") Long productId,
            @RequestParam(value = "customerPhone") String customerPhone
    ) {
        OrderSearchDto searchDto = OrderSearchDto.builder().productId(productId).customerPhone(customerPhone).build();
        return BaseResponse.ok(orderService.getInfoList(searchDto));
    }

    @PostMapping("/page/search")
    public BaseResponse<PageList<OrderInfo>> pageSearch(@RequestBody OrderSearchDto searchDto) {
        return BaseResponse.ok(orderService.pageSearch(searchDto));
    }

    @PostMapping("/insert")
    public BaseResponse<?> insert(@RequestBody OrderInsertDto orderInsertDto) {
        orderService.insert(orderInsertDto);
        return BaseResponse.ok();
    }

    @DeleteMapping("/delete/{id}")
    public BaseResponse<?> deleteOrder(@PathVariable("id") Long id) {
        orderService.delete(id);
        return BaseResponse.ok();
    }

    @PutMapping("/modify")
    public BaseResponse<?> modify(@RequestBody OrderModifyDto orderModifyDto) {
        orderService.modify(orderModifyDto);
        return BaseResponse.ok();
    }

    @GetMapping("/detail/{orderId}")
    public BaseResponse<OrderInfo> getOrderDetail(@PathVariable(value = "orderId") Long orderId) {
        return BaseResponse.ok(orderService.getOrderDetail(orderId));
    }


}

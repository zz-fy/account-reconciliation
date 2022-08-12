package cn.zfy.behalf.service;

import cn.zfy.behalf.bo.OrderInfo;
import cn.zfy.behalf.dto.biz.OrderInsertDto;
import cn.zfy.behalf.dto.biz.OrderModifyDto;
import cn.zfy.behalf.dto.biz.OrderSearchDto;
import cn.zfy.behalf.entity.po.Order;
import cn.zfy.behalf.entity.response.base.PageList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface OrderService extends IService<Order> {

    /**
     * 分页查询
     *
     * @param searchDto
     * @return
     */
    PageList<OrderInfo> pageSearch(OrderSearchDto searchDto);

    /**
     * 条件查询 最全的参数
     *
     * @param searchDto
     * @return
     */
    List<OrderInfo> getInfoList(OrderSearchDto searchDto);

    /**
     * 新增订单
     *
     * @param orderInsertDto
     */
    void insert(OrderInsertDto orderInsertDto);

    /**
     * 删除订单
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 更新订单
     *
     * @param orderModifyDto
     */
    void modify(OrderModifyDto orderModifyDto);

    /**
     * 获取订单详情
     *
     * @param orderId
     * @return
     */
    OrderInfo getOrderDetail(Long orderId);
}

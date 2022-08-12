package cn.zfy.behalf.service.impl;

import cn.zfy.behalf.bo.OrderInfo;
import cn.zfy.behalf.dto.biz.OrderInsertDto;
import cn.zfy.behalf.dto.biz.OrderModifyDto;
import cn.zfy.behalf.dto.biz.OrderSearchDto;
import cn.zfy.behalf.entity.po.Order;
import cn.zfy.behalf.entity.po.OrderCustomer;
import cn.zfy.behalf.entity.response.base.PageList;
import cn.zfy.behalf.mapper.OrderMapper;
import cn.zfy.behalf.service.OrderCustomerService;
import cn.zfy.behalf.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {


    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderCustomerService orderCustomerService;

    @Override
    public PageList<OrderInfo> pageSearch(OrderSearchDto searchDto) {
        PageMethod.startPage(searchDto.getCurrent(), searchDto.getSize());
        PageInfo<OrderInfo> pageInfo = new PageInfo<>(getInfoList(searchDto));
        PageMethod.clearPage();
        return new PageList<>(pageInfo.getList(), pageInfo.getTotal());
    }

    @Override
    public List<OrderInfo> getInfoList(OrderSearchDto searchDto) {
        return orderMapper.queryList(searchDto);
    }

    @Override
    @Transactional
    public void insert(OrderInsertDto insertDto) {
        Order order = new Order();
        BeanUtils.copyProperties(insertDto, order);
        order.setCreateTime(new Date());
        save(order);
        OrderCustomer orderCustomer = OrderCustomer.builder()
                .orderId(order.getId())
                .nickname(insertDto.getCustomerNickname())
                .realName(insertDto.getCustomerRealName())
                .phone(insertDto.getCustomerPhone())
                .address(insertDto.getCustomerAddress())
                .build();
        orderCustomerService.save(orderCustomer);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        removeById(id);
        orderCustomerService.removeByOrderId(id);
    }

    @Override
    @Transactional
    public void modify(OrderModifyDto orderModifyDto) {
        Order order = new Order();
        BeanUtils.copyProperties(orderModifyDto, order);
        updateById(order);
        OrderCustomer orderCustomer = OrderCustomer.builder()
                .id(orderModifyDto.getOrderCustomerId())
                .nickname(orderModifyDto.getCustomerNickname())
                .realName(orderModifyDto.getCustomerRealName())
                .phone(orderModifyDto.getCustomerPhone())
                .address(orderModifyDto.getCustomerAddress())
                .build();
        orderCustomerService.updateById(orderCustomer);
    }

    @Override
    public OrderInfo getOrderDetail(Long orderId) {
        OrderSearchDto searchDto = OrderSearchDto.builder().orderId(orderId).build();
        List<OrderInfo> orderInfoList = getInfoList(searchDto);
        if (CollectionUtils.isEmpty(orderInfoList)) return null;
        return orderInfoList.get(0);
    }
}

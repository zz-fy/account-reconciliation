package cn.zfy.behalf.service.impl;

import cn.zfy.behalf.entity.po.OrderCustomer;
import cn.zfy.behalf.mapper.OrderCustomerMapper;
import cn.zfy.behalf.service.OrderCustomerService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class OrderCustomerServiceImpl extends ServiceImpl<OrderCustomerMapper, OrderCustomer> implements OrderCustomerService {

    @Override
    public void removeByOrderId(Long orderId) {
        LambdaQueryWrapper<OrderCustomer> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderCustomer::getOrderId, orderId);
        remove(queryWrapper);
    }
}

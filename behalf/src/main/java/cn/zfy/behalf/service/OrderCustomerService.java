package cn.zfy.behalf.service;

import cn.zfy.behalf.entity.po.OrderCustomer;
import com.baomidou.mybatisplus.extension.service.IService;


public interface OrderCustomerService extends IService<OrderCustomer> {

    void removeByOrderId(Long orderId);
}

package cn.zfy.behalf.mapper;

import cn.zfy.behalf.bo.OrderInfo;
import cn.zfy.behalf.dto.biz.OrderSearchDto;
import cn.zfy.behalf.entity.po.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {


    /**
     * 查询订单详细信息
     *
     * @param searchDto
     * @return
     */
    List<OrderInfo> queryList(@Param("searchDto") OrderSearchDto searchDto);
}

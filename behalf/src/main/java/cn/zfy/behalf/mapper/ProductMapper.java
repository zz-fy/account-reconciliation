package cn.zfy.behalf.mapper;

import cn.zfy.behalf.dto.biz.ProductPageSearchDto;
import cn.zfy.behalf.entity.po.Product;
import cn.zfy.behalf.vo.ProductPageVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    int selectCountQuote(@Param("id") Serializable id);

    List<ProductPageVo> queryList(@Param("searchDto") ProductPageSearchDto searchDto);
}

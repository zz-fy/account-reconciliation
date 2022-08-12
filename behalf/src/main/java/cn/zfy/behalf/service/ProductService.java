package cn.zfy.behalf.service;

import cn.zfy.behalf.dto.biz.ProductInsertDto;
import cn.zfy.behalf.dto.biz.ProductModifyDto;
import cn.zfy.behalf.dto.biz.ProductModifyEnableDto;
import cn.zfy.behalf.dto.biz.ProductPageSearchDto;
import cn.zfy.behalf.entity.po.Product;
import cn.zfy.behalf.entity.response.base.PageList;
import cn.zfy.behalf.vo.ProductPageVo;
import com.baomidou.mybatisplus.extension.service.IService;


public interface ProductService extends IService<Product> {

    void insert(ProductInsertDto productInsertDto);

    void modify(ProductModifyDto productModifyDto);

    void modifyEnable(ProductModifyEnableDto modifyEnableDto);

    void delete(Long id);

    PageList<ProductPageVo> pageSearch(ProductPageSearchDto searchDto);
}

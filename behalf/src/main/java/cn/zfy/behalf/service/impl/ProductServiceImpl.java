package cn.zfy.behalf.service.impl;

import cn.zfy.behalf.dto.biz.ProductInsertDto;
import cn.zfy.behalf.dto.biz.ProductModifyDto;
import cn.zfy.behalf.dto.biz.ProductModifyEnableDto;
import cn.zfy.behalf.dto.biz.ProductPageSearchDto;
import cn.zfy.behalf.entity.po.Product;
import cn.zfy.behalf.entity.response.base.PageList;
import cn.zfy.behalf.exception.CustomException;
import cn.zfy.behalf.iface.CheckQuoteService;
import cn.zfy.behalf.mapper.ProductMapper;
import cn.zfy.behalf.service.ProductService;
import cn.zfy.behalf.vo.ProductPageVo;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService, CheckQuoteService {

    @Resource
    private ProductMapper productMapper;

    @Override
    public boolean hasQuote(Serializable id) {
        return productMapper.selectCountQuote(id) > 0;
    }

    @Override
    public void insert(ProductInsertDto productInsertDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productInsertDto, product);
        product.setCreateTime(new Date());
        save(product);
    }

    @Override
    public void modify(ProductModifyDto productModifyDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productModifyDto, product);
        updateById(product);
    }

    @Override
    public void modifyEnable(ProductModifyEnableDto modifyEnableDto) {
        LambdaUpdateWrapper<Product> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Product::getId, modifyEnableDto.getId())
                .set(Product::getEnabled, modifyEnableDto.getEnabled());
        update(updateWrapper);
    }

    @Override
    public void delete(Long id) {
        if (hasQuote(id)) throw new CustomException("该条商品存在订单哈,不能删除...");
        removeById(id);
    }

    @Override
    public PageList<ProductPageVo> pageSearch(ProductPageSearchDto searchDto) {
        PageMethod.startPage(searchDto.getCurrent(), searchDto.getSize());
        PageInfo<ProductPageVo> pageInfo = new PageInfo<>(productMapper.queryList(searchDto));
        PageMethod.clearPage();
        return new PageList<>(pageInfo.getList(), pageInfo.getTotal());
    }
}

package cn.zfy.behalf.controller;


import cn.zfy.behalf.dto.biz.ProductInsertDto;
import cn.zfy.behalf.dto.biz.ProductModifyDto;
import cn.zfy.behalf.dto.biz.ProductModifyEnableDto;
import cn.zfy.behalf.dto.biz.ProductPageSearchDto;
import cn.zfy.behalf.entity.po.Product;
import cn.zfy.behalf.entity.response.base.BaseResponse;
import cn.zfy.behalf.entity.response.base.PageList;
import cn.zfy.behalf.service.ProductService;
import cn.zfy.behalf.vo.ProductPageVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Resource
    private ProductService productService;

    @PostMapping("/insert")
    public BaseResponse<?> insert(@RequestBody ProductInsertDto productInsertDto) {
        productService.insert(productInsertDto);
        return BaseResponse.ok();
    }

    @PutMapping("/modify")
    public BaseResponse<?> insert(@RequestBody ProductModifyDto productModifyDto) {
        productService.modify(productModifyDto);
        return BaseResponse.ok();
    }

    @PutMapping("/enable")
    public BaseResponse<?> insert(@RequestBody ProductModifyEnableDto modifyEnableDto) {
        productService.modifyEnable(modifyEnableDto);
        return BaseResponse.ok();
    }


    @DeleteMapping("/delete/{id}")
    public BaseResponse<?> delete(@PathVariable(value = "id") Long id) {
        productService.delete(id);
        return BaseResponse.ok();
    }

    @PostMapping("/page/search")
    public BaseResponse<PageList<ProductPageVo>> pageSearch(@RequestBody ProductPageSearchDto searchDto) {
        return BaseResponse.ok(productService.pageSearch(searchDto));
    }

    @GetMapping("/list")
    public BaseResponse<List<Product>> list() {
        return BaseResponse.ok(productService.list());
    }


}

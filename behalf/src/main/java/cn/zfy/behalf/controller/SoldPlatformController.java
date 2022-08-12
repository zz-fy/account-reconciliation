package cn.zfy.behalf.controller;


import cn.zfy.behalf.dto.biz.SoldPlatformInsertDto;
import cn.zfy.behalf.dto.biz.SoldPlatformModifyDto;
import cn.zfy.behalf.dto.biz.SoldPlatformPageSearchDto;
import cn.zfy.behalf.entity.po.SoldPlatform;
import cn.zfy.behalf.entity.response.base.BaseResponse;
import cn.zfy.behalf.entity.response.base.PageList;
import cn.zfy.behalf.service.SoldPlatformService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 售出平台
 *
 * @author zfy
 * @since 2022-07-10
 */
@RestController
@RequestMapping("/api/sold-platform")
public class SoldPlatformController {

    @Resource
    private SoldPlatformService soldPlatformService;

    @PostMapping("/insert")
    public BaseResponse<?> insert(@RequestBody @Valid SoldPlatformInsertDto insertDto) {
        soldPlatformService.insert(insertDto);
        return BaseResponse.ok();
    }

    @PutMapping("/modify")
    public BaseResponse<?> modify(@RequestBody @Valid SoldPlatformModifyDto modifyDto) {
        soldPlatformService.modify(modifyDto);
        return BaseResponse.ok();
    }

    @DeleteMapping("/delete/{id}")
    public BaseResponse<?> delete(@PathVariable(value = "id") Long id) {
        soldPlatformService.delete(id);
        return BaseResponse.ok();
    }

    @PostMapping("/page/search")
    public BaseResponse<PageList<SoldPlatform>> pageSearch(@RequestBody SoldPlatformPageSearchDto searchDto) {
        return BaseResponse.ok(soldPlatformService.pageSearch(searchDto));
    }

    @GetMapping("/list")
    public BaseResponse<List<SoldPlatform>> list(){
        return BaseResponse.ok(soldPlatformService.list());
    }
}

package cn.zfy.behalf.controller;


import cn.zfy.behalf.dto.biz.SoldAccountPageSearchDto;
import cn.zfy.behalf.dto.biz.SoldAccountInsertDto;
import cn.zfy.behalf.dto.biz.SoldAccountModifyDto;
import cn.zfy.behalf.entity.po.SoldAccount;
import cn.zfy.behalf.entity.response.base.BaseResponse;
import cn.zfy.behalf.entity.response.base.PageList;
import cn.zfy.behalf.service.SoldAccountService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 售卖账户
 *
 * @author zfy
 * @since 2022-07-10
 */
@RestController
@RequestMapping("/api/sold-account")
public class SoldAccountController {

    @Resource
    private SoldAccountService soldAccountService;

    @PostMapping("/insert")
    public BaseResponse<?> insert(@RequestBody @Valid SoldAccountInsertDto insertDto) {
        soldAccountService.insert(insertDto);
        return BaseResponse.ok();
    }

    @PutMapping("/modify")
    public BaseResponse<?> modify(@RequestBody @Valid SoldAccountModifyDto modifyDto) {
        soldAccountService.modify(modifyDto);
        return BaseResponse.ok();
    }

    @DeleteMapping("/delete/{id}")
    public BaseResponse<?> delete(@PathVariable(value = "id") Long id) {
        soldAccountService.delete(id);
        return BaseResponse.ok();
    }

    @PostMapping("/page/search")
    public BaseResponse<PageList<SoldAccount>> pageSearch(@RequestBody SoldAccountPageSearchDto searchDto) {
        return BaseResponse.ok(soldAccountService.pageSearch(searchDto));
    }

    @GetMapping("/list")
    public BaseResponse<List<SoldAccount>> list() {
        return BaseResponse.ok(soldAccountService.list());
    }


}

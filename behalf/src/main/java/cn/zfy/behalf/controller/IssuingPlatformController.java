package cn.zfy.behalf.controller;


import cn.zfy.behalf.bo.IssuingPlatformBo;
import cn.zfy.behalf.dto.biz.IssuingPlatformInsertDto;
import cn.zfy.behalf.dto.biz.IssuingPlatformModifyDto;
import cn.zfy.behalf.dto.biz.IssuingPlatformPageSearchDto;
import cn.zfy.behalf.entity.po.IssuingPlatform;
import cn.zfy.behalf.entity.response.base.BaseResponse;
import cn.zfy.behalf.entity.response.base.PageList;
import cn.zfy.behalf.service.IssuingPlatformService;
import cn.zfy.behalf.vo.IssuingCompanyVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 代发平台
 *
 * @author zfy
 * @since 2022-07-10
 */
@RestController
@RequestMapping("/api/issuing-platform")
public class IssuingPlatformController {

    @Resource
    private IssuingPlatformService issuingPlatformService;

    @PostMapping("/insert")
    public BaseResponse<?> insert(@RequestBody @Valid IssuingPlatformInsertDto insertDto) {
        issuingPlatformService.insert(insertDto);
        return BaseResponse.ok();
    }

    @PutMapping("/modify")
    public BaseResponse<?> modify(@RequestBody @Valid IssuingPlatformModifyDto modifyDto) {
        issuingPlatformService.modify(modifyDto);
        return BaseResponse.ok();
    }

    @DeleteMapping("/delete/{id}")
    public BaseResponse<?> delete(@PathVariable(value = "id") Long id) {
        issuingPlatformService.delete(id);
        return BaseResponse.ok();
    }

    @PostMapping("/page/search")
    public BaseResponse<PageList<IssuingPlatform>> pageSearch(@RequestBody IssuingPlatformPageSearchDto searchDto) {
        return BaseResponse.ok(issuingPlatformService.pageSearch(searchDto));
    }

    @GetMapping("/list")
    public BaseResponse<List<IssuingPlatform>> list() {
        return BaseResponse.ok(issuingPlatformService.list());
    }

    @GetMapping("/tree")
    public BaseResponse<List<IssuingPlatformBo>> getIssuingPlatformTree() {
        return BaseResponse.ok(issuingPlatformService.getIssuingPlatformTree());
    }

}

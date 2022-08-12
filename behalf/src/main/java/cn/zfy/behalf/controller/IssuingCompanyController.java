package cn.zfy.behalf.controller;


import cn.zfy.behalf.dto.biz.IssuingCompanyInsertDto;
import cn.zfy.behalf.dto.biz.IssuingCompanyModifyDto;
import cn.zfy.behalf.dto.biz.IssuingCompanyPageSearchDto;
import cn.zfy.behalf.entity.response.base.BaseResponse;
import cn.zfy.behalf.entity.response.base.PageList;
import cn.zfy.behalf.service.IssuingCompanyService;
import cn.zfy.behalf.vo.IssuingCompanyVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 代发公司
 *
 * @author zfy
 * @since 2022-07-10
 */
@RestController
@RequestMapping("/api/issuing-company")
public class IssuingCompanyController {

    @Resource
    private IssuingCompanyService issuingCompanyService;

    @PostMapping("/insert")
    public BaseResponse<?> insert(@RequestBody @Valid IssuingCompanyInsertDto insertDto) {
        issuingCompanyService.insert(insertDto);
        return BaseResponse.ok();
    }

    @PutMapping("/modify")
    public BaseResponse<?> modify(@RequestBody @Valid IssuingCompanyModifyDto modifyDto) {
        issuingCompanyService.modify(modifyDto);
        return BaseResponse.ok();
    }

    @DeleteMapping("/delete/{id}")
    public BaseResponse<?> delete(@PathVariable(value = "id") Long id) {
        issuingCompanyService.delete(id);
        return BaseResponse.ok();
    }

    @PostMapping("/page/search")
    public BaseResponse<PageList<IssuingCompanyVo>> pageSearch(@RequestBody IssuingCompanyPageSearchDto searchDto) {
        return BaseResponse.ok(issuingCompanyService.pageSearch(searchDto));
    }

    @GetMapping("/list")
    public BaseResponse<List<IssuingCompanyVo>> list() {
        return BaseResponse.ok(issuingCompanyService.list());
    }


}

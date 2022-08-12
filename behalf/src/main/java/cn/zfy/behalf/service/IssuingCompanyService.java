package cn.zfy.behalf.service;

import cn.zfy.behalf.dto.biz.IssuingCompanyInsertDto;
import cn.zfy.behalf.dto.biz.IssuingCompanyModifyDto;
import cn.zfy.behalf.dto.biz.IssuingCompanyPageSearchDto;
import cn.zfy.behalf.entity.po.IssuingCompany;
import cn.zfy.behalf.entity.response.base.PageList;
import cn.zfy.behalf.vo.IssuingCompanyVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IssuingCompanyService extends IService<IssuingCompany> {

    void insert(IssuingCompanyInsertDto insertDto);

    void modify(IssuingCompanyModifyDto modifyDto);

    void delete(Long id);

    PageList<IssuingCompanyVo> pageSearch(IssuingCompanyPageSearchDto searchDto);
}

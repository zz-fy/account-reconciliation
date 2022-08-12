package cn.zfy.behalf.service;

import cn.zfy.behalf.bo.IssuingPlatformBo;
import cn.zfy.behalf.dto.biz.IssuingPlatformInsertDto;
import cn.zfy.behalf.dto.biz.IssuingPlatformModifyDto;
import cn.zfy.behalf.dto.biz.IssuingPlatformPageSearchDto;
import cn.zfy.behalf.entity.po.IssuingPlatform;
import cn.zfy.behalf.entity.response.base.PageList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface IssuingPlatformService extends IService<IssuingPlatform> {

    void insert(IssuingPlatformInsertDto insertDto);

    void modify(IssuingPlatformModifyDto modifyDto);

    void delete(Long id);

    PageList<IssuingPlatform> pageSearch(IssuingPlatformPageSearchDto searchDto);

    List<IssuingPlatformBo> getIssuingPlatformTree();

}

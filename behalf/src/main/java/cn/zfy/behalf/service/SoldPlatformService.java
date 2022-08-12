package cn.zfy.behalf.service;

import cn.zfy.behalf.dto.biz.SoldPlatformInsertDto;
import cn.zfy.behalf.dto.biz.SoldPlatformModifyDto;
import cn.zfy.behalf.dto.biz.SoldPlatformPageSearchDto;
import cn.zfy.behalf.entity.po.SoldPlatform;
import cn.zfy.behalf.entity.response.base.PageList;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SoldPlatformService extends IService<SoldPlatform> {

    void insert(SoldPlatformInsertDto insertDto);

    void modify(SoldPlatformModifyDto modifyDto);

    void delete(Long id);

    PageList<SoldPlatform> pageSearch(SoldPlatformPageSearchDto searchDto);
}

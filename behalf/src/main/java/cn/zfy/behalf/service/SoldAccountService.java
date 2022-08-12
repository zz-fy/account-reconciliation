package cn.zfy.behalf.service;

import cn.zfy.behalf.dto.biz.SoldAccountInsertDto;
import cn.zfy.behalf.dto.biz.SoldAccountModifyDto;
import cn.zfy.behalf.dto.biz.SoldAccountPageSearchDto;
import cn.zfy.behalf.entity.po.SoldAccount;
import cn.zfy.behalf.entity.response.base.PageList;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SoldAccountService extends IService<SoldAccount> {

    void insert(SoldAccountInsertDto insertDto);

    void modify(SoldAccountModifyDto modifyDto);

    void delete(Long id);

    PageList<SoldAccount> pageSearch(SoldAccountPageSearchDto searchDto);
}

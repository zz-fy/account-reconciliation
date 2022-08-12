package cn.zfy.behalf.service.impl;

import cn.zfy.behalf.dto.biz.SoldAccountInsertDto;
import cn.zfy.behalf.dto.biz.SoldAccountModifyDto;
import cn.zfy.behalf.dto.biz.SoldAccountPageSearchDto;
import cn.zfy.behalf.entity.po.SoldAccount;
import cn.zfy.behalf.entity.response.base.PageList;
import cn.zfy.behalf.exception.CustomException;
import cn.zfy.behalf.iface.CheckQuoteService;
import cn.zfy.behalf.mapper.SoldAccountMapper;
import cn.zfy.behalf.service.SoldAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;

@Service
public class SoldAccountServiceImpl extends ServiceImpl<SoldAccountMapper, SoldAccount> implements SoldAccountService, CheckQuoteService {

    @Resource
    private SoldAccountMapper soldAccountMapper;

    @Override
    @Transactional
    public void insert(SoldAccountInsertDto insertDto) {
        SoldAccount soldAccount = new SoldAccount();
        BeanUtils.copyProperties(insertDto, soldAccount);
        soldAccount.setCreateTime(new Date());
        try {
            save(soldAccount);
        } catch (Exception ex) {
            throw new CustomException("该条数据可能已经添加过了哈...");
        }
    }

    @Override
    @Transactional
    public void modify(SoldAccountModifyDto modifyDto) {
        SoldAccount soldAccount = new SoldAccount();
        BeanUtils.copyProperties(modifyDto, soldAccount);
        try {
            updateById(soldAccount);
        } catch (Exception ex) {
            throw new CustomException("该条数据可能已经添加过了哈...");
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (hasQuote(id)) throw new CustomException("该条数据已经有关联了哈..不能删除..");
        removeById(id);
    }

    @Override
    public PageList<SoldAccount> pageSearch(SoldAccountPageSearchDto searchDto) {
        PageMethod.startPage(searchDto.getCurrent(), searchDto.getSize());
        PageInfo<SoldAccount> pageInfo = new PageInfo<>(soldAccountMapper.queryList(searchDto));
        PageMethod.clearPage();
        return new PageList<>(pageInfo.getList(), pageInfo.getTotal());
    }

    @Override
    public boolean hasQuote(Serializable id) {
        return soldAccountMapper.selectCountQuote(id) > 0;
    }
}

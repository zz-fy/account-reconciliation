package cn.zfy.behalf.service.impl;

import cn.zfy.behalf.dto.biz.SoldPlatformInsertDto;
import cn.zfy.behalf.dto.biz.SoldPlatformModifyDto;
import cn.zfy.behalf.dto.biz.SoldPlatformPageSearchDto;
import cn.zfy.behalf.entity.po.SoldPlatform;
import cn.zfy.behalf.entity.response.base.PageList;
import cn.zfy.behalf.exception.CustomException;
import cn.zfy.behalf.iface.CheckQuoteService;
import cn.zfy.behalf.mapper.SoldPlatformMapper;
import cn.zfy.behalf.service.SoldPlatformService;
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
public class SoldPlatformServiceImpl extends ServiceImpl<SoldPlatformMapper, SoldPlatform> implements SoldPlatformService, CheckQuoteService {

    @Resource
    private SoldPlatformMapper soldPlatformMapper;

    @Override
    @Transactional
    public void insert(SoldPlatformInsertDto insertDto) {
        SoldPlatform soldPlatform = new SoldPlatform();
        BeanUtils.copyProperties(insertDto, soldPlatform);
        soldPlatform.setCreateTime(new Date());
        try {
            save(soldPlatform);
        } catch (Exception ex) {
            throw new CustomException("该条数据可能已经添加过了哈...");
        }
    }

    @Override
    @Transactional
    public void modify(SoldPlatformModifyDto modifyDto) {
        SoldPlatform soldPlatform = new SoldPlatform();
        BeanUtils.copyProperties(modifyDto, soldPlatform);
        try {
            updateById(soldPlatform);
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
    public PageList<SoldPlatform> pageSearch(SoldPlatformPageSearchDto searchDto) {
        PageMethod.startPage(searchDto.getCurrent(), searchDto.getSize());
        PageInfo<SoldPlatform> pageInfo = new PageInfo<>(soldPlatformMapper.queryList(searchDto));
        PageMethod.clearPage();
        return new PageList<>(pageInfo.getList(), pageInfo.getTotal());
    }

    @Override
    public boolean hasQuote(Serializable id) {
        return soldPlatformMapper.selectCountQuote(id) > 0;
    }
}

package cn.zfy.behalf.service.impl;

import cn.zfy.behalf.bo.IssuingPlatformBo;
import cn.zfy.behalf.dto.biz.IssuingPlatformInsertDto;
import cn.zfy.behalf.dto.biz.IssuingPlatformModifyDto;
import cn.zfy.behalf.dto.biz.IssuingPlatformPageSearchDto;
import cn.zfy.behalf.entity.po.IssuingCompany;
import cn.zfy.behalf.entity.po.IssuingPlatform;
import cn.zfy.behalf.entity.response.base.PageList;
import cn.zfy.behalf.exception.CustomException;
import cn.zfy.behalf.iface.CheckQuoteService;
import cn.zfy.behalf.mapper.IssuingPlatformMapper;
import cn.zfy.behalf.service.IssuingCompanyService;
import cn.zfy.behalf.service.IssuingPlatformService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IssuingPlatformServiceImpl extends ServiceImpl<IssuingPlatformMapper, IssuingPlatform> implements IssuingPlatformService, CheckQuoteService {

    @Resource
    private IssuingPlatformMapper issuingPlatformMapper;

    @Resource
    private IssuingCompanyService issuingCompanyService;

    @Override
    public void insert(IssuingPlatformInsertDto insertDto) {
        IssuingPlatform issuingPlatform = new IssuingPlatform();
        BeanUtils.copyProperties(insertDto, issuingPlatform);
        issuingPlatform.setCreateTime(new Date());
        try {
            save(issuingPlatform);
        } catch (Exception ex) {
            throw new CustomException("该条数据可能已经添加过了哈...");
        }
    }

    @Override
    public void modify(IssuingPlatformModifyDto modifyDto) {
        IssuingPlatform issuingPlatform = new IssuingPlatform();
        BeanUtils.copyProperties(modifyDto, issuingPlatform);
        try {
            updateById(issuingPlatform);
        } catch (Exception ex) {
            throw new CustomException("该条数据可能已经添加过了哈...");
        }
    }

    @Override
    public void delete(Long id) {
        if (hasQuote(id)) throw new CustomException("该条数据已经有关联了哈..不能删除..");
        removeById(id);
    }

    @Override
    public PageList<IssuingPlatform> pageSearch(IssuingPlatformPageSearchDto searchDto) {
        PageMethod.startPage(searchDto.getCurrent(), searchDto.getSize());
        PageInfo<IssuingPlatform> pageInfo = new PageInfo<>(issuingPlatformMapper.queryList(searchDto));
        PageMethod.clearPage();
        return new PageList<>(pageInfo.getList(), pageInfo.getTotal());
    }

    @Override
    public List<IssuingPlatformBo> getIssuingPlatformTree() {
        List<IssuingPlatform> issuingPlatformList = list();
        if (CollectionUtils.isEmpty(issuingPlatformList)) return Collections.emptyList();
        List<IssuingPlatformBo> platformBoList = issuingPlatformList.stream().map(platform -> {
            IssuingPlatformBo bo = new IssuingPlatformBo();
            BeanUtils.copyProperties(platform, bo);
            return bo;
        }).collect(Collectors.toList());
        List<IssuingCompany> issuingCompanyList = issuingCompanyService.list();
        if (CollectionUtils.isEmpty(issuingCompanyList)) return platformBoList;
        platformBoList.forEach(bo -> bo.setIssuingCompanyList(issuingCompanyList.stream().filter(issuingCompany -> issuingCompany.getIssuingPlatformId().equals(bo.getId())).collect(Collectors.toList())));
        return platformBoList;
    }

    @Override
    public boolean hasQuote(Serializable id) {
        return issuingPlatformMapper.selectCountQuote(id) > 0;
    }

}

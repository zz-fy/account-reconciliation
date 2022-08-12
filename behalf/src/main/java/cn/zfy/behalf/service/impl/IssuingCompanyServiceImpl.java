package cn.zfy.behalf.service.impl;

import cn.zfy.behalf.dto.biz.IssuingCompanyInsertDto;
import cn.zfy.behalf.dto.biz.IssuingCompanyModifyDto;
import cn.zfy.behalf.dto.biz.IssuingCompanyPageSearchDto;
import cn.zfy.behalf.entity.po.IssuingCompany;
import cn.zfy.behalf.entity.response.base.PageList;
import cn.zfy.behalf.exception.CustomException;
import cn.zfy.behalf.iface.CheckQuoteService;
import cn.zfy.behalf.mapper.IssuingCompanyMapper;
import cn.zfy.behalf.service.IssuingCompanyService;
import cn.zfy.behalf.vo.IssuingCompanyVo;
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
public class IssuingCompanyServiceImpl extends ServiceImpl<IssuingCompanyMapper, IssuingCompany> implements IssuingCompanyService, CheckQuoteService {

    @Resource
    private IssuingCompanyMapper issuingCompanyMapper;

    @Override
    @Transactional
    public void insert(IssuingCompanyInsertDto insertDto) {
        IssuingCompany issuingCompany = new IssuingCompany();
        BeanUtils.copyProperties(insertDto, issuingCompany);
        issuingCompany.setCreateTime(new Date());
        try {
            save(issuingCompany);
        } catch (Exception ex) {
            throw new CustomException("该条数据可能已经添加过了哈...");
        }
    }

    @Override
    @Transactional
    public void modify(IssuingCompanyModifyDto modifyDto) {
        IssuingCompany issuingCompany = new IssuingCompany();
        BeanUtils.copyProperties(modifyDto, issuingCompany);
        try {
            updateById(issuingCompany);
        } catch (Exception ex) {
            throw new CustomException("该条数据可能已经添加过了哈...");
        }
    }

    @Transactional
    public void delete(Long id) {
        if (hasQuote(id)) throw new CustomException("该条数据已经有关联了哈..不能删除..");
        removeById(id);
    }

    @Override
    public boolean hasQuote(Serializable id) {
        return issuingCompanyMapper.countQuote(id) > 0;
    }


    @Override
    public PageList<IssuingCompanyVo> pageSearch(IssuingCompanyPageSearchDto searchDto) {
        PageMethod.startPage(searchDto.getCurrent(), searchDto.getSize());
        PageInfo<IssuingCompanyVo> pageInfo = new PageInfo<>(issuingCompanyMapper.queryList(searchDto));
        PageMethod.clearPage();
        return new PageList<>(pageInfo.getList(), pageInfo.getTotal());
    }
}

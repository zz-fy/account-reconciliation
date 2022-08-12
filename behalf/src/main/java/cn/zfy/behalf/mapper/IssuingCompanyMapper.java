package cn.zfy.behalf.mapper;

import cn.zfy.behalf.dto.biz.IssuingCompanyPageSearchDto;
import cn.zfy.behalf.entity.po.IssuingCompany;
import cn.zfy.behalf.vo.IssuingCompanyVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

@Mapper
public interface IssuingCompanyMapper extends BaseMapper<IssuingCompany> {


    /**
     * 查看引用的个数
     *
     * @param id
     * @return
     */
    int countQuote(Serializable id);

    /**
     * 模糊查询
     *
     * @param searchDto
     * @return
     */
    List<IssuingCompanyVo> queryList(@Param("searchDto") IssuingCompanyPageSearchDto searchDto);
}

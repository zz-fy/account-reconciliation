package cn.zfy.behalf.mapper;

import cn.zfy.behalf.dto.biz.SoldAccountPageSearchDto;
import cn.zfy.behalf.entity.po.SoldAccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

@Mapper
public interface SoldAccountMapper extends BaseMapper<SoldAccount> {

    List<SoldAccount> queryList(@Param("searchDto") SoldAccountPageSearchDto searchDto);

    int selectCountQuote(Serializable id);
}

package cn.zfy.behalf.mapper;

import cn.zfy.behalf.dto.biz.SoldPlatformPageSearchDto;
import cn.zfy.behalf.entity.po.SoldPlatform;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

@Mapper
public interface SoldPlatformMapper extends BaseMapper<SoldPlatform> {

    int selectCountQuote(Serializable id);

    List<SoldPlatform> queryList(@Param("searchDto") SoldPlatformPageSearchDto searchDto);
}

package cn.zfy.behalf.mapper;

import cn.zfy.behalf.dto.biz.IssuingPlatformPageSearchDto;
import cn.zfy.behalf.entity.po.IssuingPlatform;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

@Mapper
public interface IssuingPlatformMapper extends BaseMapper<IssuingPlatform> {

    /**
     * 查看引用个数
     *
     * @param id
     * @return
     */
    int selectCountQuote(Serializable id);

    List<IssuingPlatform> queryList(@Param("searchDto") IssuingPlatformPageSearchDto searchDto);
}

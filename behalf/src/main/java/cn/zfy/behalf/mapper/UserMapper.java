package cn.zfy.behalf.mapper;

import cn.zfy.behalf.entity.po.User;
import cn.zfy.behalf.dto.biz.UserPageSearchDto;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> search(@Param("searchDto") UserPageSearchDto searchDto);

}

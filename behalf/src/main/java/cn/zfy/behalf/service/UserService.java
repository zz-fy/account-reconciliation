package cn.zfy.behalf.service;

import cn.zfy.behalf.entity.po.User;
import cn.zfy.behalf.dto.biz.UserInsertDto;
import cn.zfy.behalf.dto.biz.UserPageSearchDto;
import cn.zfy.behalf.entity.response.base.PageList;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User> {


    /**
     * 分页查询用户
     *
     * @param searchDto
     * @return
     */
    PageList<User> pageSearch(UserPageSearchDto searchDto);

    /**
     * 新增用户
     *
     * @param insertDto
     */
    void insert(UserInsertDto insertDto);

    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    User getByUserName(String username);
}

package cn.zfy.behalf.service.impl;

import cn.zfy.behalf.constants.AppConstants;
import cn.zfy.behalf.entity.po.User;
import cn.zfy.behalf.dto.biz.UserInsertDto;
import cn.zfy.behalf.dto.biz.UserPageSearchDto;
import cn.zfy.behalf.entity.response.base.PageList;
import cn.zfy.behalf.exception.CustomException;
import cn.zfy.behalf.mapper.UserMapper;
import cn.zfy.behalf.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Resource
    private UserMapper userMapper;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public User getByUserName(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        return getOne(queryWrapper);
    }


    @Override
    public PageList<User> pageSearch(UserPageSearchDto searchDto) {
        PageMethod.startPage(searchDto.getCurrent(), searchDto.getSize());
        PageInfo<User> pageInfo = new PageInfo<>(userMapper.search(searchDto));
        PageMethod.clearPage();
        return new PageList<>(pageInfo.getList(), pageInfo.getTotal());
    }

    @Override
    public void insert(UserInsertDto insertDto) {
        User user = new User();
        BeanUtils.copyProperties(insertDto, user);
        String password = StringUtils.isEmpty(insertDto.getPassword()) ? AppConstants.DEFAULT_PWD : String.valueOf(Base64Utils.decodeFromString(insertDto.getPassword()));
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setCreateTime(new Date());
        try {
            save(user);
        } catch (Exception e) {
            throw new CustomException(String.format("已经存在登录名为'%s'的用户", user.getUsername()));
        }
    }
}

package cn.zfy.behalf.controller;

import cn.zfy.behalf.entity.po.User;
import cn.zfy.behalf.dto.biz.UserInsertDto;
import cn.zfy.behalf.dto.biz.UserPageSearchDto;
import cn.zfy.behalf.entity.response.base.BaseResponse;
import cn.zfy.behalf.entity.response.base.PageList;
import cn.zfy.behalf.exception.CustomException;
import cn.zfy.behalf.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 分页查询用户
     *
     * @return
     */
    @PostMapping("/page/search")
    public BaseResponse<PageList<User>> pageSearch(@RequestBody UserPageSearchDto searchDto) {
        return BaseResponse.ok(userService.pageSearch(searchDto));
    }

    /**
     * 新增用户
     *
     * @param insertDto
     * @return
     */
    @PostMapping("/insert")
    public BaseResponse<?> insert(@Valid @RequestBody UserInsertDto insertDto) {
        userService.insert(insertDto);
        return BaseResponse.ok();
    }

    /**
     * 删除用户
     *
     * @param userId
     * @return
     */
    @DeleteMapping("/delete/{userId}")
    public BaseResponse<?> delete(@PathVariable(value = "userId") Long userId) {
        int count = userService.count();
        if (count == 1) {
            throw new CustomException("系统中只有一位用户了,不能删除了...");
        }
        userService.removeById(userId);
        return BaseResponse.ok();
    }

}


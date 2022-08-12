package cn.zfy.behalf.dto.base;

import lombok.Data;

/**
 * @Classname PageRequest
 * @Description 分页请求
 */
@Data
public class PageRequest {

    private Integer current;

    private Integer size;


}

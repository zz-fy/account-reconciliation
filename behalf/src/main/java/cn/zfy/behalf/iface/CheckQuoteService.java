package cn.zfy.behalf.iface;

import java.io.Serializable;

/**
 * @Classname CheckQuoteService
 * @Description 检查要删除的记录是否被引用
 * @Created by zfy
 */
public interface CheckQuoteService {

    boolean hasQuote(Serializable id);

}

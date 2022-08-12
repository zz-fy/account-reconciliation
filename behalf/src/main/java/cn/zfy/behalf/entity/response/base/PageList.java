package cn.zfy.behalf.entity.response.base;

import java.util.Collections;
import java.util.List;

/**
 * 分页列表 实体类
 */
public class PageList<T> {
    private List<T> list;

    private long recordCount;

    /**
     * 列表
     */
    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    /**
     * 记录总数
     */
    public long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(long recordCount) {
        this.recordCount = recordCount;
    }

    public PageList() {
    }

    public PageList(List<T> list, long recordCount) {
        this.setList(list);
        this.setRecordCount(recordCount);
    }

    /**
     * 空分页列表
     *
     * @return 空分页列表
     */
    public static PageList emptyPageList() {
        return new PageList(Collections.emptyList(), 0l);
    }
}

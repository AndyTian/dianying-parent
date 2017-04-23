package com.tianl.dianying.common.page;

import lombok.Getter;
import lombok.Setter;

/**
 * 含有分页查询条件的类
 *
 * Created by Administrator on 2016/12/24.
 */
public class PageCondition {
    /**
     * 页码
     */
    @Getter
    @Setter
    private Integer pageNumber;

    /**
     * 每页显示条数
     */
    @Getter
    @Setter
    private Integer pageSize;

    /**
     * 总条数
     */
    @Getter
    @Setter
    private Integer totalSize;
}

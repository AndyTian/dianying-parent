package com.tianl.dianying.service;

import com.tianl.dianying.common.core.service.ISimpleTableService;
import com.tianl.dianying.common.page.PageCondition;
import com.tianl.dianying.entity.Ord;

import java.util.List;

/**
 * Created by Administrator on 2017/2/8.
 */
public interface OrdService extends ISimpleTableService<Ord>{
    List<Ord> findAll(PageCondition condition);

    List<Ord> findByUserId(long userId);
}

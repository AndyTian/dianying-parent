package com.tianl.dianying.service;

import com.tianl.dianying.common.core.service.ISimpleTableService;
import com.tianl.dianying.common.page.PageCondition;
import com.tianl.dianying.entity.Pingjia;

import java.util.List;

/**
 * Created by Administrator on 2017/2/8.
 */
public interface PingjiaService extends ISimpleTableService<Pingjia>{
    List<Pingjia> findAll(PageCondition condition);

    List<Pingjia> findByMovieId(long id);
}

package com.tianl.dianying.service;

import com.tianl.dianying.common.core.service.ISimpleTableService;
import com.tianl.dianying.common.page.PageCondition;
import com.tianl.dianying.entity.Play;

import java.util.List;

/**
 * Created by Administrator on 2017/2/8.
 */
public interface PlayService extends ISimpleTableService<Play>{
    List<Play> findAll(PageCondition condition);

    List<Play> findByCondition(long movieId, long cinemaId);
}

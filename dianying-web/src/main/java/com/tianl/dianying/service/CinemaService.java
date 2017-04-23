package com.tianl.dianying.service;

import com.tianl.dianying.common.core.service.ISimpleTableService;
import com.tianl.dianying.common.page.PageCondition;
import com.tianl.dianying.entity.Cinema;

import java.util.List;

/**
 * Created by Administrator on 2017/2/8.
 */
public interface CinemaService extends ISimpleTableService<Cinema>{
    List<Cinema> findAll(PageCondition condition);

    List<Cinema> findAllCinema();
}

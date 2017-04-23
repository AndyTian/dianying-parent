package com.tianl.dianying.service;

import com.tianl.dianying.common.core.service.ISimpleTableService;
import com.tianl.dianying.common.page.PageCondition;
import com.tianl.dianying.entity.Movie;

import java.util.List;

/**
 * Created by Administrator on 2016/12/28.
 */
public interface MovieService extends ISimpleTableService<Movie>{

    List<Movie> findAll(PageCondition condition);

    List<Movie> findAllMovie();
}

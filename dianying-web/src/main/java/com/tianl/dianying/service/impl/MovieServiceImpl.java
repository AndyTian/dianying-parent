package com.tianl.dianying.service.impl;

import com.tianl.dianying.common.core.persistence.BaseMapper;
import com.tianl.dianying.common.core.service.SimpleTableService;
import com.tianl.dianying.common.page.PageCondition;
import com.tianl.dianying.common.page.Pager;
import com.tianl.dianying.entity.Movie;
import com.tianl.dianying.entity.example.MovieExample;
import com.tianl.dianying.mapper.MovieMapper;
import com.tianl.dianying.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/12/28.
 */
@Service
public class MovieServiceImpl extends SimpleTableService<Movie> implements MovieService {

    @Autowired
    private MovieMapper movieMapper;

    protected BaseMapper<Movie> getBaseMapper() {
        return movieMapper;
    }

    public List<Movie> findAll(PageCondition condition) {
        MovieExample example = new MovieExample();
        condition.setTotalSize(movieMapper.countByExample(example));
        Pager<Movie> pager = new Pager<Movie>();
        pager.setPageNumber(condition.getPageNumber());
        pager.setPageSize(condition.getPageSize());
        List<Movie> list = movieMapper.selectByExample(example,pager);
        return list;
    }

    public List<Movie> findAllMovie() {
        MovieExample example = new MovieExample();
        example.appendCriterion("1=1");
        return queryList(example);
    }
}

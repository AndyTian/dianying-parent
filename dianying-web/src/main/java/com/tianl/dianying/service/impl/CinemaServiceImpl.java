package com.tianl.dianying.service.impl;

import com.tianl.dianying.common.core.persistence.BaseMapper;
import com.tianl.dianying.common.core.service.SimpleTableService;
import com.tianl.dianying.common.page.PageCondition;
import com.tianl.dianying.common.page.Pager;
import com.tianl.dianying.entity.Cinema;
import com.tianl.dianying.entity.example.CinemaExample;
import com.tianl.dianying.mapper.CinemaMapper;
import com.tianl.dianying.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/2/8.
 */
@Service
public class CinemaServiceImpl extends SimpleTableService<Cinema> implements CinemaService{

    @Autowired
    private CinemaMapper cinemaMapper;

    protected BaseMapper<Cinema> getBaseMapper() {
        return cinemaMapper;
    }

    public List<Cinema> findAll(PageCondition condition) {
        CinemaExample example = new CinemaExample();
        condition.setTotalSize(getBaseMapper().countByExample(example));
        Pager<Cinema> pager = new Pager<Cinema>();
        pager.setPageSize(condition.getPageSize());
        pager.setPageNumber(condition.getPageNumber());
        List<Cinema> list = getBaseMapper().selectByExample(example, pager);
        return list;
    }

    public List<Cinema> findAllCinema() {
        CinemaExample example = new CinemaExample();
        example.appendCriterion("1=1");
        return queryList(example);
    }
}

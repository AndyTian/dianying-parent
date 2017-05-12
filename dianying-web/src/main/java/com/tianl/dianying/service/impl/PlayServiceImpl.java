package com.tianl.dianying.service.impl;

import com.tianl.dianying.common.core.persistence.BaseMapper;
import com.tianl.dianying.common.core.service.SimpleTableService;
import com.tianl.dianying.common.page.PageCondition;
import com.tianl.dianying.common.page.Pager;
import com.tianl.dianying.entity.Play;
import com.tianl.dianying.entity.example.PlayExample;
import com.tianl.dianying.mapper.PlayMapper;
import com.tianl.dianying.service.PlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/2/8.
 */
@Service
public class PlayServiceImpl extends SimpleTableService<Play> implements PlayService{

    @Autowired
    private PlayMapper playMapper;


    protected BaseMapper<Play> getBaseMapper() {
        return playMapper;
    }

    public List<Play> findAll(PageCondition condition) {
        PlayExample example = new PlayExample();
        condition.setTotalSize(getBaseMapper().countByExample(example));
        Pager<Play> pager = new Pager<Play>();
        pager.setPageNumber(condition.getPageNumber());
        pager.setPageSize(condition.getPageSize());
        List<Play> list = getBaseMapper().selectByExample(example, pager);
        return  list;
    }

    public List<Play> findByCondition(long movieId, long cinemaId) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        PlayExample example = new PlayExample();
        example.appendCriterion("movieId = ", movieId);
        example.appendCriterion("cinemaId = ", cinemaId);
        example.appendCriterion("str_to_date(showtime,'%Y-%m-%d %H:%i') >= ", df.format(new Date()));
        example.setOrderByClause("showtime asc");
        List<Play> list = getBaseMapper().selectByExample(example,null);
        return list;
    }
}

package com.tianl.dianying.service.impl;

import com.tianl.dianying.common.core.persistence.BaseMapper;
import com.tianl.dianying.common.core.service.SimpleTableService;
import com.tianl.dianying.common.page.PageCondition;
import com.tianl.dianying.common.page.Pager;
import com.tianl.dianying.entity.Pingjia;
import com.tianl.dianying.entity.example.PingjiaExample;
import com.tianl.dianying.mapper.PingjiaMapper;
import com.tianl.dianying.service.PingjiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/2/8.
 */
@Service
public class PingjiaServiceImpl extends SimpleTableService<Pingjia> implements PingjiaService{

    @Autowired
    private PingjiaMapper pingjiaMapper;

    protected BaseMapper<Pingjia> getBaseMapper() {
        return pingjiaMapper;
    }

    public List<Pingjia> findAll(PageCondition condition) {
        PingjiaExample example = new PingjiaExample();
        condition.setTotalSize(getBaseMapper().countByExample(example));
        Pager<Pingjia> pager = new Pager<Pingjia>();
        pager.setPageNumber(condition.getPageNumber());
        pager.setPageSize(condition.getPageSize());
        List<Pingjia> list = getBaseMapper().selectByExample(example, pager);
        return list;
    }

    public List<Pingjia> findByMovieId(long id) {
        PingjiaExample example = new PingjiaExample();
        example.appendCriterion("movieId = ", id);
        List<Pingjia> list = getBaseMapper().selectByExample(example, null);
        return list;
    }
}

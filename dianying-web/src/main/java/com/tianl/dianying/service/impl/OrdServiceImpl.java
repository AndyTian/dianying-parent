package com.tianl.dianying.service.impl;

import com.tianl.dianying.common.core.persistence.BaseMapper;
import com.tianl.dianying.common.core.service.SimpleTableService;
import com.tianl.dianying.common.page.PageCondition;
import com.tianl.dianying.common.page.Pager;
import com.tianl.dianying.entity.Ord;
import com.tianl.dianying.entity.example.OrdExample;
import com.tianl.dianying.mapper.OrdMapper;
import com.tianl.dianying.service.OrdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/2/8.
 */
@Service
public class OrdServiceImpl extends SimpleTableService<Ord> implements OrdService{

    @Autowired
    private OrdMapper ordMapper;

    protected BaseMapper<Ord> getBaseMapper() {
        return ordMapper;
    }

    public List<Ord> findAll(PageCondition condition) {
        OrdExample example = new OrdExample();
        condition.setTotalSize(getBaseMapper().countByExample(example));
        Pager<Ord> pager = new Pager<Ord>();
        pager.setPageNumber(condition.getPageNumber());
        pager.setPageSize(condition.getPageSize());
        List<Ord> list = getBaseMapper().selectByExample(example, pager);
        return list;
    }

    public List<Ord> findByUserId(long userId) {
        OrdExample example = new OrdExample();
        example.appendCriterion("userId = ",userId);
        List<Ord> list = getBaseMapper().selectByExample(example,null);
        return list;
    }
}

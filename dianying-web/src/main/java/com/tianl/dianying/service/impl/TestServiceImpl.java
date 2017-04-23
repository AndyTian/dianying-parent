package com.tianl.dianying.service.impl;

import com.tianl.dianying.common.core.example.TestExample;
import com.tianl.dianying.common.core.persistence.BaseMapper;
import com.tianl.dianying.common.core.service.SimpleTableService;
import com.tianl.dianying.common.page.Pager;
import com.tianl.dianying.mapper.TestMapper;
import com.tianl.dianying.entity.Test;
import com.tianl.dianying.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/12/17.
 */
@Service
public class TestServiceImpl extends SimpleTableService<Test> implements TestService {

    @Autowired
    private TestMapper testMapper;

    protected BaseMapper<Test> getBaseMapper() {
        return testMapper;
    }

    public String test() {
       return testMapper.test();
    }

    public List<Test> findAll() {
        TestExample example = new TestExample();
        Pager<Test> pager = new Pager<Test>();
        pager.setPageNumber(1);
        pager.setPageSize(2);
        return testMapper.selectByExample(example,pager);
    }
}

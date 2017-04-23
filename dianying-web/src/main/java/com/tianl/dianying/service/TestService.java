package com.tianl.dianying.service;

import com.tianl.dianying.common.core.service.ISimpleTableService;
import com.tianl.dianying.entity.Test;

import java.util.List;

/**
 * Created by Administrator on 2016/12/17.
 */
public interface TestService extends ISimpleTableService<Test>{
    public String test();

    public List<Test> findAll();
}

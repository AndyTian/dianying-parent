package com.tianl.dianying.service.impl;

import com.tianl.dianying.common.core.persistence.BaseMapper;
import com.tianl.dianying.common.core.service.SimpleTableService;
import com.tianl.dianying.common.page.PageCondition;
import com.tianl.dianying.common.page.Pager;
import com.tianl.dianying.entity.User;
import com.tianl.dianying.entity.example.UserExample;
import com.tianl.dianying.mapper.UserMapper;
import com.tianl.dianying.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/12/23.
 */
@Service
public class UserServiceImpl extends SimpleTableService<User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    protected BaseMapper<User> getBaseMapper() {
        return userMapper;
    }

    public List<User> findAll(PageCondition condition) {
        UserExample example = new UserExample();
        condition.setTotalSize(getBaseMapper().countByExample(example));
        Pager<User> pager = new Pager<User>();
        pager.setPageNumber(condition.getPageNumber());
        pager.setPageSize(condition.getPageSize());
        List<User> list = getBaseMapper().selectByExample(example,pager);
        return list;
    }

    public List<User> findALLUser() {
        UserExample example = new UserExample();
        example.appendCriterion("1=1");
        return queryList(example);
    }

    public User findByUsername(String username) {
        UserExample example = new UserExample();
        example.appendCriterion("username = ", username);
        return getBaseMapper().selectOneByExample(example);
    }
}

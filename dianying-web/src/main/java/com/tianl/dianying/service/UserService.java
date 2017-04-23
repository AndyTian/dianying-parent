package com.tianl.dianying.service;

import com.tianl.dianying.common.core.service.ISimpleTableService;
import com.tianl.dianying.common.page.PageCondition;
import com.tianl.dianying.entity.User;

import java.util.List;

/**
 * Created by Administrator on 2016/12/23.
 */
public interface UserService extends ISimpleTableService<User>{
    List<User> findAll(PageCondition condition);

    List<User> findALLUser();

    User findByUsername(String username);

}

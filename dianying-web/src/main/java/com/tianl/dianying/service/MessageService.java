package com.tianl.dianying.service;

import com.tianl.dianying.common.core.service.ISimpleTableService;
import com.tianl.dianying.common.page.PageCondition;
import com.tianl.dianying.entity.Message;

import java.util.List;

/**
 * Created by Administrator on 2017/2/8.
 */
public interface MessageService extends ISimpleTableService<Message>{
    List<Message> findAll(PageCondition condition);
}

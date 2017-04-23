package com.tianl.dianying.service.impl;

import com.tianl.dianying.common.core.persistence.BaseMapper;
import com.tianl.dianying.common.core.service.SimpleTableService;
import com.tianl.dianying.common.page.PageCondition;
import com.tianl.dianying.common.page.Pager;
import com.tianl.dianying.entity.Message;
import com.tianl.dianying.entity.example.MessageExample;
import com.tianl.dianying.mapper.MessageMapper;
import com.tianl.dianying.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/2/8.
 */
@Service
public class MessageServiceImpl extends SimpleTableService<Message> implements MessageService{

    @Autowired
    private MessageMapper messageMapper;

    protected BaseMapper<Message> getBaseMapper() {
        return messageMapper;
    }

    public List<Message> findAll(PageCondition condition) {
        MessageExample example = new MessageExample();
        condition.setTotalSize(getBaseMapper().countByExample(example));
        Pager<Message> pager = new Pager<Message>();
        pager.setPageNumber(condition.getPageNumber());
        pager.setPageSize(condition.getPageSize());
        List<Message> list = getBaseMapper().selectByExample(example, pager);
        return list;
    }
}

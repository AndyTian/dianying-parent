package com.tianl.dianying.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/12/21.
 */
@Component
public class TestScheduler {
    /**
     * 日志记录器
     */
    protected final Logger logger = LoggerFactory.getLogger(getClass());

//    @Scheduled(cron = "0/5 * * * * ?")
//    public void test(){
//        System.out.println("test");
//    }
}

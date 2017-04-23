package com.tianl.dianying.entity;

import com.tianl.dianying.common.pojo.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Created by Administrator on 2017/2/8.
 */
@Table(name = "message")
public class Message extends BaseEntity{

    @Column
    @Getter
    @Setter
    private long userId;

    @Column
    @Getter
    @Setter
    private String content;

    @Column
    @Getter
    @Setter
    private int state;

}

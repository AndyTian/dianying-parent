package com.tianl.dianying.entity;

import com.tianl.dianying.common.pojo.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Created by Administrator on 2017/2/8.
 */
@Table(name = "pingjia")
public class Pingjia extends BaseEntity{

    @Getter
    @Setter
    private User user;

    @Column
    @Getter
    @Setter
    private long userId;

    @Column
    @Getter
    @Setter
    private long movieId;

    @Column
    @Getter
    @Setter
    private String content;
}

package com.tianl.dianying.entity;

import com.tianl.dianying.common.pojo.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Created by Administrator on 2017/2/8.
 */
@Table(name = "ord")
public class Ord extends BaseEntity{

    @Getter
    @Setter
    private User user;

    @Getter
    @Setter
    private Play play;

    @Column
    @Getter
    @Setter
    private long userId;

    @Column
    @Getter
    @Setter
    private long playId;

    @Column
    @Getter
    @Setter
    private String ticketNum;

    @Column
    @Getter
    @Setter
    private int payState;

}

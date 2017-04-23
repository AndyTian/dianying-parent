package com.tianl.dianying.entity;

import com.tianl.dianying.common.pojo.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/12/17.
 */
@Table(name = "test")
public class Test extends BaseEntity{

    @Column
    @Getter
    @Setter
    private String test;

}

package com.tianl.dianying.entity;

import com.tianl.dianying.common.pojo.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Created by Administrator on 2017/2/8.
 */
@Table(name = "cinema")
public class Cinema extends BaseEntity{

    @Column
    @Getter
    @Setter
    private String name;

    @Column
    @Getter
    @Setter
    private String summary;

    @Column
    @Getter
    @Setter
    private String pic;

}

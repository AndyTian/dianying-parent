package com.tianl.dianying.entity;

import com.tianl.dianying.common.pojo.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/28.
 */
@Table(name = "movie")
public class Movie extends BaseEntity{

    @Column
    @Getter
    @Setter
    private String name;

    @Column
    @Getter
    @Setter
    private String pic;

    @Column
    @Getter
    @Setter
    private String director;

    @Column
    @Getter
    @Setter
    private String stars;

    @Column
    @Getter
    @Setter
    private String summary;

    @Column
    @Getter
    @Setter
    private Date showtime;


}

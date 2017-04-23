package com.tianl.dianying.entity;

import com.tianl.dianying.common.pojo.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Administrator on 2017/2/8.
 */
@Table(name = "play")
public class Play extends BaseEntity{

    @Getter
    @Setter
    private Movie movie;

    @Column
    @Getter
    @Setter
    private long movieId;

    @Column
    @Getter
    @Setter
    private long cinemaId;

    @Column
    @Getter
    @Setter
    private String videoHall;

    @Column
    @Getter
    @Setter
    private Date showtime;

    @Column
    @Getter
    @Setter
    private String seatNum;
}

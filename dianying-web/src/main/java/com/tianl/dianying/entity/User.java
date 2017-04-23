package com.tianl.dianying.entity;

import com.tianl.dianying.common.pojo.BaseEntity;
import com.tianl.dianying.enumeration.GenderType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/12/23.
 */
@Table(name = "user")
public class User extends BaseEntity{

    @Column
    @Getter
    @Setter
    private String username;

    @Column
    @Getter
    @Setter
    private String password;

    @Column
    @Getter
    @Setter
    private String nickname;

    @Column
    @Getter
    @Setter
    private String name;

    @Column
    @Getter
    @Setter
    private GenderType gender;

    @Column
    @Getter
    @Setter
    private String phonenumber;

}

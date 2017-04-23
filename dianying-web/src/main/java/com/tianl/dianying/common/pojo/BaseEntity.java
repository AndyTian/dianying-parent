package com.tianl.dianying.common.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/15.
 */
public abstract class BaseEntity implements IEntity, Serializable {

    /**
     * ID
     */
    @Column
    private Long id;

    /**
     * 创建日期
     */
    @Column
    protected Date createDate;

    /**
     * 修改日期
     */
    @Column
    protected Date modifyDate;

    public Date getCreateDate() {
        return createDate;
    }

    public Long getId() {
        return id;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setCreateDate(final Date createDate) {
        this.createDate = createDate;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setModifyDate(final Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }
}

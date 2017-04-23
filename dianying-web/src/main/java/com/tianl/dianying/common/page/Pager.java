package com.tianl.dianying.common.page;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/15.
 */
@JsonWriteNullProperties(value = false)
public class Pager<E> implements Serializable {
    public static final Integer MAX_PAGE_SIZE = 50000;// 每页最大记录数限制

    private static final long serialVersionUID = 1L;

    private List<E> list;// 数据List
    private Integer pageCount = 0;// 总页数
    private Integer pageNumber = 1;// 当前页码
    private Integer pageSize = 20;// 每页记录数
    @JsonIgnore
    private Integer startRow;
    private Integer totalCount = 0;// 总记录数

    public Pager() {

    }

    public Pager(final Integer pageNumber, final Integer pageSize) {
        Assert.notNull(pageNumber);
        Assert.notNull(pageSize);
        this.setPageNumber(pageNumber);
        this.setPageSize(pageSize);
    }

    /**
     * 获取limit 结束的数量（为了程序方便）
     * */
    @JsonIgnore
    public Integer getEndNumber() {
        return this.pageSize;
    }

    public List<E> getList() {
        return list;
    }

    /**
     * 冗余的方法 等待更加好的
     * */
    @JsonIgnore
    public Integer getP() {
        return pageNumber;
    }

    @JsonIgnore
    public Integer getPageCount() {
        return pageCount;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * 获取limit 开始的数量（为了程序方便）
     * */
    @JsonIgnore
    public Integer getStartNumber() {
        if (pageNumber < 2) {
            return 0;
        }
        return (this.pageNumber - 1) * this.pageSize;
    }

    @JsonIgnore
    public Long getStartRecord() {
        return (pageNumber - 1L) * pageSize;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    @JsonIgnore
    public boolean isEmpty() {
        if (list == null || list.size() == 0) {
            return true;
        }
        return false;
    }

    @JsonIgnore
    public boolean isForward() {
        if (this.pageNumber <= 1) {
            return false;
        }
        return true;
    }

    @JsonIgnore
    public boolean isNext() {
        if (this.pageCount > this.pageNumber) {
            return true;
        }
        return false;
    }

    public void setList(final List<E> list) {
        this.list = list;
    }

    /**
     * 冗余的方法 等待更加好的
     * */
    public void setP(final Integer pageNumber) {
        this.setPageNumber(pageNumber);
    }

    public void setPageCount(final Integer pageCount) {
        this.pageCount = pageCount;
    }

    public void setPageNumber(Integer pageNumber) {
        if (pageNumber < 1) {
            pageNumber = 1;
        }
        this.pageNumber = pageNumber;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize < 1) {
            pageSize = 1;
        } else if (pageSize > MAX_PAGE_SIZE) {
            pageSize = MAX_PAGE_SIZE;
        }
        this.pageSize = pageSize;
    }

    @JsonIgnore
    public void setStartRow(final Integer startRow) {
        this.startRow = startRow;
    }

    public void setTotalCount(final Integer totalCount) {
        this.totalCount = totalCount;
        if (pageSize == 0) {
            pageCount = 0;
        } else {
            pageCount = (totalCount + pageSize - 1) / pageSize;
        }
    }

    @Override
    public String toString() {
        return "Pager [list=" + list + ", pageCount=" + pageCount
                + ", pageNumber=" + pageNumber + ", pageSize=" + pageSize
                + ", startRow=" + startRow + ", totalCount=" + totalCount
                + ", isForward()=" + isForward() + ", isNext()=" + isNext()
                + ", isEmpty()=" + isEmpty() + "]";
    }
}


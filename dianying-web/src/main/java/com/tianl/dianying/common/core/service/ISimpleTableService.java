package com.tianl.dianying.common.core.service;

import com.tianl.dianying.common.core.example.Example;
import com.tianl.dianying.common.pojo.BaseEntity;

import java.util.List;

/**
 * Created by Administrator on 2016/12/16.
 */
public interface ISimpleTableService<T extends BaseEntity> {
    /**
     * @return 查询总条数
     */
    int countByExample(Example<T> example);

    /**
     * 使用主键删除实体
     *
     * @param id
     * @return 是否成功
     */
    boolean deleteById(final Long id);

    /**
     * 使用主键查询实体
     *
     * @param id
     *            实体主键
     * @return 对应的实体，如果不存在返回null
     */
    T findById(final Long id);

    /**
     * 新增数据
     *
     * @param entity
     *            待新增的数据实体
     *
     * @return 主键
     */
    long insert(final T entity);

    /**
     * 更新数据
     *
     * @param tobeUpdate
     *            待更新数据
     * @return 是否成功
     */
    boolean update(final T tobeUpdate);

    /**
     * 列表查询
     *
     * @param example
     * @return
     */
    List<T> queryList(final Example<T> example);

    /**
     * 列表查询
     *
     * @param example
     * @param pageNumber
     *            第几页
     * @param pageSize
     *            每页多少条
     * @return
     */
    List<T> queryList(final Example<T> example, final int pageNumber,
                      final int pageSize);
}


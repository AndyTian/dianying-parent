package com.tianl.dianying.common.core.persistence;

import com.tianl.dianying.common.core.example.Example;
import com.tianl.dianying.common.page.Pager;
import com.tianl.dianying.common.pojo.BaseEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Administrator on 2016/12/15.
 */
public interface  BaseMapper<T extends BaseEntity> {
    @SelectProvider(type = BaseDaoTemplate.class, method = "countByExample")
    int countByExample(@Param("example") Example<T> example);

    @SelectProvider(type = BaseDaoTemplate.class, method = "countColByExample")
    int countColByExample(@Param("col") String col,
                          @Param("example") Example<T> example);

    @DeleteProvider(type = BaseDaoTemplate.class, method = "deleteByExample")
    int deleteByExample(@Param("example") Example<T> example);

    @DeleteProvider(type = BaseDaoTemplate.class, method = "deleteByPrimaryKey")
    int deleteByPrimaryKey(T entity);

    @InsertProvider(type = BaseDaoTemplate.class, method = "insertSelective")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(T record);

    @InsertProvider(type = BaseDaoTemplate.class, method = "insert")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertWithNullValue(T record);

    @SelectProvider(type = BaseDaoTemplate.class, method = "selectByExample")
    @ResultMap("ListMap")
    List<T> selectByExample(@Param("example") Example<T> example,
                            @Param("pager") Pager<T> pager);

    @ResultMap("DetailMap")
    T selectByPrimaryKey(Long id);

    @SelectProvider(type = BaseDaoTemplate.class, method = "selectOneByExample")
    @ResultMap("DetailMap")
    T selectOneByExample(@Param("example") Example<T> example);

    @SelectProvider(type = BaseDaoTemplate.class, method = "sumByExample")
    Double sumByExample(@Param("field") String field,
                        @Param("example") Example<T> example);

    @UpdateProvider(type = BaseDaoTemplate.class, method = "updateByExampleSelective")
    int updateByExample(@Param("record") T record,
                        @Param("example") Example<T> example);

    @UpdateProvider(type = BaseDaoTemplate.class, method = "updateByExample")
    int updateByExampleWithNullValue(@Param("record") T record,
                                     @Param("example") Example<T> example);

    @UpdateProvider(type = BaseDaoTemplate.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKey(T record);

    @UpdateProvider(type = BaseDaoTemplate.class, method = "updateByPrimaryKey")
    int updateByPrimaryKeyWithNullValue(T record);

}


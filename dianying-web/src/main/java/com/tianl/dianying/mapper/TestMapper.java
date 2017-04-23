package com.tianl.dianying.mapper;

import com.tianl.dianying.common.core.persistence.BaseMapper;
import com.tianl.dianying.entity.Test;
import org.apache.ibatis.annotations.Select;

/**
 * Created by Administrator on 2016/12/17.
 */
public interface TestMapper extends BaseMapper<Test>{

   @Select("SELECT test FROM test WHERE id = '1'")
   public  String test();
}

/**
 *
 */
package com.tianl.dianying.common.core.service;


import com.tianl.dianying.common.core.example.Example;
import com.tianl.dianying.common.core.persistence.BaseMapper;
import com.tianl.dianying.common.page.Pager;
import com.tianl.dianying.common.pojo.BaseEntity;
import java.util.List;


public abstract class SimpleTableService<T extends BaseEntity>
		implements ISimpleTableService<T>{

	protected abstract BaseMapper<T> getBaseMapper();


	public int countByExample(Example<T> example) {
		return getBaseMapper().countByExample(example);
	}

	public boolean deleteById(Long id) {
		T t = getBaseMapper().selectByPrimaryKey(id);
		return getBaseMapper().deleteByPrimaryKey(t) == 1;
	}

	public T findById(Long id) {
		return getBaseMapper().selectByPrimaryKey(id);
	}

	public long insert(T entity) {
		return getBaseMapper().insert(entity);
	}

	public boolean update(T tobeUpdate) {
		return getBaseMapper().updateByPrimaryKey(tobeUpdate) == 1;
	}

	public List<T> queryList(Example<T> example) {
		return queryList(example, 3000);
	}

	public List<T> queryList(Example<T> example, int pageNumber, int pageSize) {
		Pager<T> pager = null;
		if (pageSize > 0) {
			pager = new Pager<T>(pageNumber, pageSize);
		}

		final List<T> entrys = getBaseMapper().selectByExample(example, pager);
		return entrys;
	}

	/**
	 * 列表查询,默认查询pageSize项
	 *
	 * @return
	 */
	public List<T> queryList(final Example<T> example, final int pageSize) {
		return queryList(example, 1, pageSize);
	}
}

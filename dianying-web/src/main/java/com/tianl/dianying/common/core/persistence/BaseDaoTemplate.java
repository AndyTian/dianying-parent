package com.tianl.dianying.common.core.persistence;

import com.tianl.dianying.common.core.example.Example;
import com.tianl.dianying.common.page.Pager;
import com.tianl.dianying.common.pojo.BaseEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/15.
 */
public class BaseDaoTemplate<T extends BaseEntity> {
    /**
     * Logger
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public String countByExample(final MapperMethod.ParamMap<?> map) {
        final Example<?> example = (Example<?>) map.get("example");
        final String sql = new SQL() {
            {
                SELECT("count(id)");
                FROM(example.getTableName());
                exampleWhereClause(this, example);
            }
        }.toString();
        logger.debug(sql.replaceAll("\n", " "));
        return sql;
    }

    public String countColByExample(final MapperMethod.ParamMap<?> map) {
        final String col = (String) map.get("col");
        final Example<?> example = (Example<?>) map.get("example");
        final String sql = new SQL() {
            {
                SELECT("count(" + col + ")");
                FROM(example.getTableName());
                exampleWhereClause(this, example);
            }
        }.toString();
        logger.debug(sql.replaceAll("\n", " "));
        return sql;
    }

    public String deleteByExample(final Example<?> example) {
        final String sql = new SQL() {
            {
                DELETE_FROM(example.getTableName());
                exampleWhereClause(this, example);
            }
        }.toString();
        logger.debug(sql.replaceAll("\n", " "));
        return sql;
    }

    public String deleteByPrimaryKey(final T entity) {
        final String sql = new SQL() {
            {
                DELETE_FROM(getTableName(entity));
                WHERE("id=#{id}");
            }
        }.toString();
        logger.debug(sql.replaceAll("\n", " "));
        return sql;
    }

    public String insert(final T entity) {

        final String sql = new SQL() {
            {
                INSERT_INTO(getTableName(entity));
                VALUES(returnInsertColumnsName(entity, false),
                        returnInsertColumnsDefine(entity, false));
            }
        }.toString();

        logger.debug(sql.replaceAll("\n", " "));
        return sql;
    }

    public String insertSelective(final T entity) {
        final String sql = new SQL() {
            {
                INSERT_INTO(getTableName(entity));
                VALUES(returnInsertColumnsName(entity, true),
                        returnInsertColumnsDefine(entity, true));
            }
        }.toString();

        logger.debug(sql.replaceAll("\n", " "));
        return sql;
    }

    @SuppressWarnings("unchecked")
    public String selectByExample(final MapperMethod.ParamMap<?> map) {
        final Example<?> example = (Example<?>) map.get("example");
        final Pager<T> pager = map.get("pager") == null ? null : (Pager<T>) map
                .get("pager");
        String sql = new SQL() {
            {
                SELECT("*");
                FROM(example.getTableName());
                exampleWhereClause(this, example);
            }
        }.toString();

        if (StringUtils.isNotBlank(example.getOrderByClause())) {
            sql += (" ORDER BY " + example.getOrderByClause());
        }

        if (pager != null) {
            sql += " limit " + pager.getStartNumber() + ","
                    + pager.getPageSize();
        }

        logger.debug(sql.replaceAll("\n", " "));
        return sql;
    }

    public String selectByPrimaryKey(final T entity) {
        final String sql = new SQL() {
            {
                SELECT("*");
                FROM(getTableName(entity));
                WHERE("id=#{id}");
            }
        }.toString();

        logger.debug(sql.replaceAll("\n", " "));
        return sql;
    }

    public String selectOneByExample(final MapperMethod.ParamMap<?> map) {
        final Example<?> example = (Example<?>) map.get("example");
        String sql = new SQL() {
            {
                SELECT("*");
                FROM(example.getTableName());
                exampleWhereClause(this, example);
            }
        }.toString();

        if (StringUtils.isNotBlank(example.getOrderByClause())) {
            sql += (" ORDER BY " + example.getOrderByClause());
        }

        sql += " limit 0,1";

        logger.debug(sql.replaceAll("\n", " "));
        return sql;
    }

    public String sumByExample(final MapperMethod.ParamMap<?> map) {
        final String field = (String) map.get("field");
        final Example<?> example = (Example<?>) map.get("example");
        final String sql = new SQL() {
            {
                SELECT("sum(" + field + ")");
                FROM(example.getTableName());
                exampleWhereClause(this, example);
            }
        }.toString();
        logger.debug(sql.replaceAll("\n", " "));
        return sql;
    }

    @SuppressWarnings("unchecked")
    public String updateByExample(final MapperMethod.ParamMap<?> map) {
        final T entity = (T) map.get("record");
        final Example<T> example = (Example<T>) map.get("example");

        final String sql = new SQL() {
            {
                UPDATE(getTableName(entity));
                final Map<Field, Object> fields = getEntityFields(
                        entity.getClass(), entity);
                for (final Field field : fields.keySet()) {
                    String columnName = getColumnName(entity, field);
                    final Object value = fields.get(field);
                    if (value == null
                            && BaseEntity.class.isAssignableFrom(field
                            .getType())) {
                        SET(columnName + "=null");
                    } else if (value instanceof BaseEntity) {
                        SET(columnName + "=#{record." + field.getName()
                                + ".id}");
                    } else {
                        SET(columnName + "=#{record." + field.getName() + "}");
                    }

                }
                exampleWhereClause(this, example);
            }
        }.toString();
        logger.debug(sql.replaceAll("\n", " "));
        return sql;
    }

    /**
     * 获取实体属性在数据库中的真实字段名
     *
     * @param entity
     *            实体
     * @param field
     *            实体属性
     * @return
     */
    private String getColumnName(T entity, Field field) {
        /**
         * 在数据库中的真实字段名第一优先从Column注解中获取
         */
        Column column = field.getAnnotation(Column.class);

        if (StringUtils.isNotBlank(column.name())) {
            return column.name();
        }
        /**
         * 实体的属性也为一个实体，及嵌套实体的时候，数据库中的字段名默认为实体名加"_id"
         */
        if (isAssignableFromBaseEntity(entity, field)) {
            return field.getName() + "_id";
        }
        /**
         * 数据库真实字段名默认与实体属性相同
         */
        return field.getName();
    }

    /**
     * 判断属性继承与BaseEntity
     *
     * @param field
     * @param value
     * @return
     */
    private boolean isAssignableFromBaseEntity(T entity, Field field) {
        Object value = ReflectionUtils.getField(field, entity);

        if (value == null && BaseEntity.class.isAssignableFrom(field.getType())) {
            return true;
        }
        if (value != null && value instanceof BaseEntity) {
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public String updateByExampleSelective(final MapperMethod.ParamMap<?> map) {
        final T entity = (T) map.get("record");
        final Example<T> example = (Example<T>) map.get("example");
        final String sql = new SQL() {
            {
                UPDATE(getTableName(entity));
                final Map<Field, Object> fields = getEntityFields(
                        entity.getClass(), entity);
                for (final Field field : fields.keySet()) {
                    String columnName = getColumnName(entity, field);
                    final Object value = fields.get(field);
                    if (value != null) {
                        if (value instanceof BaseEntity) {
                            SET(columnName + "=#{record." + field.getName()
                                    + ".id}");
                        } else {
                            SET(columnName + "=#{record." + field.getName()
                                    + "}");
                        }
                    }

                }
                exampleWhereClause(this, example);
            }
        }.toString();
        logger.debug(sql.replaceAll("\n", " "));
        return sql;

    }

    public String updateByPrimaryKey(final T entity) {
        final String sql = new SQL() {
            {
                UPDATE(getTableName(entity));
                final Map<Field, Object> fields = getEntityFields(
                        entity.getClass(), entity);
                for (final Field field : fields.keySet()) {
                    String columnName = getColumnName(entity, field);
                    final Object value = fields.get(field);
                    if (value == null
                            && BaseEntity.class.isAssignableFrom(field
                            .getType())) {
                        SET(columnName + "=null");
                    } else if (value instanceof BaseEntity) {
                        SET(columnName + "=#{" + field.getName() + ".id}");
                    } else {
                        SET(columnName + "=#{" + field.getName() + "}");
                    }

                }
                WHERE("id=#{id}");
            }
        }.toString();
        logger.debug(sql.replaceAll("\n", " "));
        return sql;
    }

    public String updateByPrimaryKeySelective(final T entity) {
        final String sql = new SQL() {
            {
                UPDATE(getTableName(entity));
                final Map<Field, Object> fields = getEntityFields(
                        entity.getClass(), entity);
                for (final Field field : fields.keySet()) {
                    String columnName = getColumnName(entity, field);
                    final Object value = fields.get(field);
                    if (value != null) {
                        if (value instanceof BaseEntity) {
                            SET(columnName + "=#{" + field.getName() + ".id}");
                        } else {
                            SET(columnName + "=#{" + field.getName() + "}");
                        }
                    }

                }
                WHERE("id=#{id}");
            }
        }.toString();
        logger.debug(sql.replaceAll("\n", " "));
        return sql;
    }

    private SQL exampleWhereClause(final SQL sql, final Example<?> example) {
        int orCount = 0;
        int i = 0;
        for (final Example.Criteria criteria : example.getOredCriteria()) {
            if (orCount != 0) {
                sql.OR();
            }
            orCount++;
            if (criteria.isValid()) {
                int j = 0;
                for (final Example.Criterion criterion : criteria.getCriteria()) {
                    if (criterion.isNoValue()) {
                        sql.WHERE(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        // sql.WHERE(criterion.getCondition() +
                        // criterion.getValue());
                        sql.WHERE(criterion.getCondition()
                                + "#{example.oredCriteria[" + i + "].criteria["
                                + j + "].value}");
                    } else if (criterion.isBetweenValue()) {
                        // sql.WHERE(criterion.getCondition() +
                        // criterion.getValue() + " and " +
                        // criterion.getSecondValue());
                        sql.WHERE(criterion.getCondition()
                                + "#{example.oredCriteria[" + i + "].criteria["
                                + j + "].value}" + " and "
                                + "#{example.oredCriteria[" + i + "].criteria["
                                + j + "].secondValue}");
                    } else if (criterion.isListValue()) {
                        final StringBuilder valueBuilder = new StringBuilder(
                                "(");
                        int vCount = 0;
                        for (@SuppressWarnings("unused")
                        final Object v : (List<?>) criterion.getValue()) {
                            if (vCount != 0) {
                                valueBuilder.append(",");
                            }
                            vCount++;
                            // valueBuilder.append(v);
                            valueBuilder.append("#{example.oredCriteria[" + i
                                    + "].criteria[" + j + "].value["
                                    + (vCount - 1) + "]}");
                        }
                        valueBuilder.append(")");
                        sql.WHERE(criterion.getCondition()
                                + valueBuilder.toString());
                    }
                    j++;
                }
            }
            i++;
        }
        return sql;
    }

    private Map<Field, Object> getEntityFields(final Class<?> clazz,
                                               final T entity) {
        Map<Field, Object> result = null;
        try {
            result = new HashMap<Field, Object>();
            for (final Field field : clazz.getDeclaredFields()) {

                if (!field.isAnnotationPresent(Column.class)) {
                    continue;
                }

                field.setAccessible(true);
                result.put(field, field.get(entity));
            }
            final Class<?> superClass = clazz.getSuperclass();
            if (superClass != null && !superClass.equals(Object.class)) {
                result.putAll(getEntityFields(superClass, entity));
            }
        } catch (IllegalAccessException e) {

        }catch (IllegalArgumentException e) {

        }
        return result;
    }

    private String returnInsertColumnsName(T entity, final boolean skipNull) {
        final StringBuilder sb = new StringBuilder();

        final Map<Field, Object> fields = getEntityFields(entity.getClass(),
                entity);

        int i = 0;
        for (final Field field : fields.keySet()) {
            boolean isNull = isNull(entity, field);

            if (skipNull && isNull) {
                continue;
            }

            if (i++ != 0) {
                sb.append(',');
            }

            String columnName = getColumnName(entity, field);
            sb.append(columnName);
        }
        return sb.toString();
    }

    private String returnInsertColumnsDefine(T entity, final boolean skipNull) {
        final StringBuilder sb = new StringBuilder();

        final Map<Field, Object> fields = getEntityFields(entity.getClass(),
                entity);

        int i = 0;
        for (final Field field : fields.keySet()) {
            final boolean isNull = isNull(entity, field);

            if (skipNull && isNull) {
                continue;
            }
            if (i++ != 0) {
                sb.append(',');
            }

            if (isNull) {
                sb.append("null");
            } else if (isAssignableFromBaseEntity(entity, field)) {
                sb.append("#{").append(field.getName()).append(".id")
                        .append('}');
            } else {
                sb.append("#{").append(field.getName()).append('}');
            }
        }
        return sb.toString();
    }

    private boolean isNull(T entity, final Field field) {
        try {
            field.setAccessible(true);
            return field.get(entity) == null;
        } catch (final SecurityException e) {
            e.printStackTrace();
        } catch (final IllegalArgumentException e) {
            e.printStackTrace();
        } catch (final IllegalAccessException e) {
            e.printStackTrace();
        }

        return false;
    }

    private String getTableName(T entity) {
        final Table table = entity.getClass().getAnnotation(Table.class);
        if (table != null) {
            return "`" + table.name() + "`";
        } else {
            return "`" + entity.getClass().getSimpleName() + "`";
        }
    }
}


package com.zjc.service.dao.mapper;


import com.zjc.service.dao.entity.TestTable;

/**
 * 测试mapper
 *
 * @param:
 * @return:
 * @author:Zhang jc
 * @date: 2018/12/28 14:28
 */
public interface TestTableMapper {

    /**
     * 插入
     *
     * @param record
     * @return
     */
    int insertSelective(TestTable record);

    /**
     * 查询全部
     *
     * @return
     */
    TestTable queryAll();
}
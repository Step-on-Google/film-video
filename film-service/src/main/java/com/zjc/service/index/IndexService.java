package com.zjc.service.index;

import com.zjc.dao.entity.TestTable;

import java.util.List;

/**
 * @Author: zhangjiachen
 * @Date: 2018/11/15 17:38
 * @Description:
 */
public interface IndexService {

    /**
     * 展示首页数据
     */
    public List<TestTable> testDao();
}

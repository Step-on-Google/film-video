package com.zjc.service.serviceimpl.index;

import com.zjc.dao.entity.TestTable;
import com.zjc.dao.mapper.TestTableMapper;
import com.zjc.service.index.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zhangjiachen
 * @Date: 2018/11/15 17:38
 * @Description:
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private TestTableMapper testTableMapper;

    @Override
    public List<TestTable> testDao() {
        return testTableMapper.queryAll();
    }
}

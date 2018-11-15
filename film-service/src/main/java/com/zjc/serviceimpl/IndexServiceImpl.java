package com.zjc.serviceimpl;

import com.zjc.index.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author: zhangjiachen
 * @Date: 2018/11/15 17:38
 * @Description:
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void showIndexData() {
    }
}

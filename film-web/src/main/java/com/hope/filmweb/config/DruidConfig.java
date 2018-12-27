package com.hope.filmweb.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Author: zhangjiachen
 * @Date: 2018/12/24 13:58
 * @Description: 德鲁伊连接池配置
 */
@Slf4j
@Configuration
@ServletComponentScan
public class DruidConfig {

    @Autowired
    private DataSourceConfig dataSourceConfig;

    @Bean(destroyMethod = "close")
    @ConditionalOnMissingBean
    public DataSource druidDataSource() {
        DruidDataSource dataSource = null;
        try {
            dataSource = new DruidDataSource();
            dataSource.setUsername(dataSourceConfig.getUsername());
            dataSource.setPassword(dataSourceConfig.getPassword());
            dataSource.setUrl(dataSourceConfig.getUrl());
            dataSource.setDriverClassName(dataSourceConfig.getDriverClassName());
            dataSource.setInitialSize(dataSourceConfig.getInitialSize());
            dataSource.setMinIdle(dataSourceConfig.getMinIdle());
            dataSource.setMaxActive(dataSourceConfig.getMaxActive());
            dataSource.setMaxWait(dataSourceConfig.getMaxWait());
            dataSource.setRemoveAbandoned(dataSourceConfig.isRemoveAbandoned());
            dataSource.setRemoveAbandonedTimeout(dataSourceConfig.getRemoveAbandonedTimeout());
            dataSource.setTimeBetweenEvictionRunsMillis(dataSourceConfig.getTimeBetweenEvictionRunsMillis());
            dataSource.setTestWhileIdle(dataSourceConfig.isTestWhileIdle());
            dataSource.setTestOnBorrow(dataSourceConfig.isTestOnBorrow());
            dataSource.setValidationQuery(dataSourceConfig.getValidationQuery());
            dataSource.setValidationQueryTimeout(dataSourceConfig.getValidationQueryTimeout());
            dataSource.setFilters(dataSourceConfig.getFilters());
            dataSource.setConnectionProperties(dataSourceConfig.getConnectionProperties());
            dataSource.setUseGlobalDataSourceStat(dataSourceConfig.isUseGlobalDataSourceStat());
        } catch (SQLException e) {
            log.info("初始化德鲁伊连接池异常!", e);
        }
        log.info("连接池初始化成功{}", dataSource.getUrl());
        return dataSource;
    }

    /**
     * mybatis分页插件
     *
     * @return
     */
    @Bean
    public PageHelper pageHelper() {
        log.info("注册MyBatis分页插件PageHelper");
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        // 设置为true时，会将RowBounds第一个参数offset当成pageNum页码使用
//		p.setProperty("offsetAsPageNum", "true");
        // 设置为true时，使用RowBounds分页会进行count查询
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        p.setProperty("dialect", "mysql");
        pageHelper.setProperties(p);
        return pageHelper;
    }
}

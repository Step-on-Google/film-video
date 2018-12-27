package com.hope.filmweb.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author: zhangjiachen
 * @Date: 2018/12/27 15:47
 * @Description:
 */
@Data
@Configuration
@PropertySource("classpath:/druid.properties")
@ConfigurationProperties(prefix = "spring.datasource.druid")
public class DataSourceConfig {
    /**
     * 数据库连接地址
     */
    private String url;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 数据库驱动
     */
    private String driverClassName;
    /**
     * 用来检测连接是否有效的sql，要求是一个查询语句。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会其作用。
     */
    private String validationQuery;
    /**
     * 单位：秒，检测连接是否有效的超时时间。底层调用jdbc Statement对象的void setQueryTimeout(int seconds)方法
     */
    private int validationQueryTimeout;
    /**
     * 最大连接池数量
     */
    private int maxActive;
    /**
     * 获取连接时最大等待时间，单位毫秒。配置了maxWait之后，缺省启用公平锁，并发效率会有所下降，如果需要可以通过配置useUnfairLock属性为true使用非公平锁。
     */
    private int maxWait;
    /**
     * 初始化时建立物理连接的个数。初始化发生在显示调用init方法，或者第一次getConnection时
     */
    private int initialSize;
    /**
     * 最大连接池数量
     */
    private int maxIdle;
    /**
     * 最小连接池数量
     */
    private int minIdle;
    /**
     * 是否自动回收超时连接
     */
    private boolean removeAbandoned;
    /**
     * 超时时间(以秒数为单位)
     */
    private int removeAbandonedTimeout;
    /**
     * 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能
     */
    private boolean testOnBorrow;
    /**
     * 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
     */
    private boolean testWhileIdle;
    /**
     * 有两个含义：1) Destroy线程会检测连接的间隔时间，如果连接空闲时间大于等于minEvictableIdleTimeMillis则关闭物理连接 2) testWhileIdle的判断依据，详细看testWhileIdle属性的说明
     */
    private Long timeBetweenEvictionRunsMillis;
    private String filters;
    private String connectionProperties;
    private boolean useGlobalDataSourceStat;
}

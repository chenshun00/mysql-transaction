package top.huzhurong.mysql.transaction.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author chenshun00@gmail.com
 * @since 2019/5/4
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class DataSourceConfig implements EnvironmentAware {
    private Environment environment;

    @Bean(initMethod = "init", destroyMethod = "close")
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUsername(this.environment.getProperty("spring.datasource.username"));
        druidDataSource.setPassword(this.environment.getProperty("spring.datasource.password"));
        druidDataSource.setUrl(this.environment.getProperty("spring.datasource.url"));
        druidDataSource.setDriverClassName(this.environment.getProperty("spring.datasource.driver-class-name"));
        druidDataSource.setDbType(this.environment.getProperty("spring.datasource.type"));
        druidDataSource.setInitialSize(Integer.parseInt(this.environment.getProperty("spring.datasource.initialSize")));
        druidDataSource.setMaxActive(Integer.parseInt(this.environment.getProperty("spring.datasource.maxActive")));
        druidDataSource.setMaxWait(Integer.parseInt(this.environment.getProperty("spring.datasource.maxWait")));
        druidDataSource.setValidationQuery(this.environment.getProperty("spring.datasource.validationQuery"));
        druidDataSource.setTestWhileIdle(Boolean.parseBoolean(this.environment.getProperty("spring.datasource.testWhileIdle")));
        druidDataSource.setTestOnBorrow(Boolean.parseBoolean(this.environment.getProperty("spring.datasource.testOnBorrow")));
        druidDataSource.setTestOnReturn(Boolean.parseBoolean(this.environment.getProperty("spring.datasource.testOnReturn")));
        return druidDataSource;
    }


    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}

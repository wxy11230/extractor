package com.dtb.metadatahub.config;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan(basePackages={"com.dtb.metadatahub.repository.mysql"}, sqlSessionFactoryRef="masterSqlSessionFactory")
public class MasterDataSourceConfig {
    static final String PACKAGE = "com.dtb.metadatahub.repository.mysql";
    static final String MAPPER_LOCATION = "classpath:mybatis/mapper/master/**/*.xml";
    @Value(value="${mapper.master.datasource.url}")
    private String url;
    @Value(value="${mapper.master.datasource.username}")
    private String user;
    @Value(value="${mapper.master.datasource.password}")
    private String password;
    @Value(value="${mapper.master.datasource.driverClassName}")
    private String driverClass;

    @Bean(name={"masterDataSource"})
    @Primary
    public DataSource masterDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(this.driverClass);
        dataSource.setJdbcUrl(this.url);
        dataSource.setUsername(this.user);
        dataSource.setPassword(this.password);
        dataSource.setReadOnly(false);
        dataSource.setConnectionTimeout(30000L);
        dataSource.setIdleTimeout(600000L);
        dataSource.setMaxLifetime(1800000L);
        dataSource.setMaximumPoolSize(15);
        return dataSource;
    }

    @Bean(name={"masterTransactionManager"})
    @Primary
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(this.masterDataSource());
    }

    @Bean(name={"masterSqlSessionFactory"})
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier(value="masterDataSource") DataSource masterDataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        sessionFactory.setConfiguration(configuration);
        return sessionFactory.getObject();
    }
}

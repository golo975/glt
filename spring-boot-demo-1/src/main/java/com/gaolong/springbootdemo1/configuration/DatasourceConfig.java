package com.gaolong.springbootdemo1.configuration;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "glt.datasource.master")
    public DataSource getDataSource() {
        return new MysqlDataSource();
    }
}

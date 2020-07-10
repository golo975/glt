package com.gaolong.springbootdemo1.configuration;

import com.gaolong.springbootdemo1.util.DatasourceSelector;
import com.mysql.cj.jdbc.MysqlDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DatasourceConfig {

    public enum DatasourceEnum {
        MASTER,
        SLAVE_1,
        SLAVE_2,
        ;
    }

    @Primary
    @Bean(name = "master")
    @ConfigurationProperties("glt.datasource.master")
    public DataSource master() {
        return DataSourceBuilder.create().type(MysqlDataSource.class).build();
    }

    @Bean(name = "slave1")
    @ConfigurationProperties("glt.datasource.slave1")
    public DataSource slave1() {
        return DataSourceBuilder.create().type(MysqlDataSource.class).build();
    }

    @Bean(name = "slave2")
    @ConfigurationProperties("glt.datasource.slave2")
    public DataSource slave2() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name = "multiRoutingDataSource")
    public DataSource multiRoutingDataSource() {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DatasourceEnum.MASTER, master());
        targetDataSources.put(DatasourceEnum.SLAVE_1, slave1());
        targetDataSources.put(DatasourceEnum.SLAVE_2, slave2());

        MultiRoutingDataSource multiRoutingDataSource = new MultiRoutingDataSource();
        multiRoutingDataSource.setDefaultTargetDataSource(master());
        multiRoutingDataSource.setTargetDataSources(targetDataSources);
        return multiRoutingDataSource;
    }

    public static class MultiRoutingDataSource extends AbstractRoutingDataSource {
        @Override
        protected Object determineCurrentLookupKey() {
            return DatasourceSelector.getCurrentDb();
        }
    }
}

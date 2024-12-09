package com.example.datawarehouseDBwarehouse.Config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean(name = "dbControllerDataSource")
    public DataSource dbControllerDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url("${db.controller.url}")
                .username("${db.controller.username}")
                .password("${db.controller.password}")
                .build();
    }

    @Bean(name = "dbStagingDataSource")
    public DataSource dbStagingDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url("${db.staging.url}")
                .username("${db.staging.username}")
                .password("${db.staging.password}")
                .build();
    }

    @Bean(name = "dbWarehouseDataSource")
    @Primary
    public DataSource dbWarehouseDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url("${db.warehouse.url}")
                .username("${db.warehouse.username}")
                .password("${db.warehouse.password}")
                .build();
    }
}




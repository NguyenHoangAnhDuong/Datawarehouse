package com.example.datawarehouseDBwarehouse.Util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConnector {

    // Kết nối DB Controller
    @Value("${db.controller.url}")
    private String controllerUrl;
    @Value("${db.controller.username}")
    private String controllerUsername;
    @Value("${db.controller.password}")
    private String controllerPassword;

    // Kết nối DB Staging
    @Value("${db.staging.url}")
    private String stagingUrl;
    @Value("${db.staging.username}")
    private String stagingUsername;
    @Value("${db.staging.password}")
    private String stagingPassword;

    // Kết nối DB Warehouse
    @Value("${db.warehouse.url}")
    private String warehouseUrl;
    @Value("${db.warehouse.username}")
    private String warehouseUsername;
    @Value("${db.warehouse.password}")
    private String warehousePassword;

    // Bean cho DB Controller
    @Bean(name = "controllerDataSource")
    public DataSource controllerDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(controllerUrl);
        dataSource.setUsername(controllerUsername);
        dataSource.setPassword(controllerPassword);
        return dataSource;
    }
    // Bean cho DB Staging
    @Bean(name = "stagingDataSource")
    public DataSource stagingDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(stagingUrl);
        dataSource.setUsername(stagingUsername);
        dataSource.setPassword(stagingPassword);
        return dataSource;
    }

    // Bean cho DB Warehouse
    @Bean(name = "warehouseDataSource")
    public DataSource warehouseDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(warehouseUrl);
        dataSource.setUsername(warehouseUsername);
        dataSource.setPassword(warehousePassword);
        return dataSource;
    }
}

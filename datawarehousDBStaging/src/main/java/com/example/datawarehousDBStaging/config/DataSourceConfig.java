package com.example.datawarehousDBStaging.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

  @Bean(name = "controllerDataSource")
  @Primary
  public DataSource controllerDataSource() {
    return DataSourceBuilder.create()
        .url("jdbc:postgresql://localhost:5454/DBController")
        .username("user")
        .password("password")
        .driverClassName("org.postgresql.Driver")
        .build();
  }

  @Bean(name = "stagingDataSource")
  public DataSource stagingDataSource() {
    return DataSourceBuilder.create()
        .url("jdbc:postgresql://localhost:5455/DBStaging")
        .username("user")
        .password("password")
        .driverClassName("org.postgresql.Driver")
        .build();
  }

  @Bean(name = "controllerJdbcTemplate")
  public JdbcTemplate controllerJdbcTemplate(@Qualifier("controllerDataSource") DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }

  @Bean(name = "stagingJdbcTemplate")
  public JdbcTemplate stagingJdbcTemplate(@Qualifier("stagingDataSource") DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }
}
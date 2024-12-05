package com.example.datawarehousDBStaging.config;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;



@Configuration
public class StagingDataSourceConfig {

  @Autowired
  @Qualifier("controllerJdbcTemplate")
    private JdbcTemplate controllerJdbcTemplate;  

  @Autowired
  private ControllerDataSourceConfig controllerDataSourceConfig;


  @Bean(name = "stagingDataSource") 
  public DataSource stagingDataSource() {
    Map<String, String> dbInfo = controllerDataSourceConfig.getStagingDBInfo(controllerJdbcTemplate);
    String url = String.format("jdbc:postgresql://%s:%s/%s", dbInfo.get("host"), dbInfo.get("port"), dbInfo.get("name"));
    return DataSourceBuilder.create()
    .url(url)
    .username(dbInfo.get("username"))
    .password(dbInfo.get("password"))
    .driverClassName("org.postgresql.Driver")
    .build();

  }


   
  @Bean(name= "stagingJdbcTemplate")
  public JdbcTemplate stagingJdbcTemplate(@Qualifier("stagingDataSource") DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }


}





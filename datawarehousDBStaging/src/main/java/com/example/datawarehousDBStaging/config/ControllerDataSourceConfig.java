package com.example.datawarehousDBStaging.config;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@Configuration
public class ControllerDataSourceConfig {


  @Bean(name= "controllerDataSource")
  public DataSource controllerDataSource() {
    return DataSourceBuilder.create()
    .url("jdbc:mysql://localhost:5454/DBController").username("user")
    .password("password")
    .driverClassName("org.postgresql.Driver")
    .build() ;
  }


  @Bean(name = "controllerJdbcTemplate")
  public JdbcTemplate controllerJdbcTemplate(@Qualifier("controllerDataSource") DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }

  /**
   * Lấy thông tin về các thuộc tính liên quan đến Staging.
   */

  public Map<String, String> getStagingDBInfo(JdbcTemplate jdbcTemplate) {

        String sql = "SELECT staging_src_username, staging_src_pwd, staging_src_port, staging_db_name, staging_src_host  FROM configs WHERE status = active";

        return jdbcTemplate.queryForObject(sql, new RowMapper<Map<String, String>>() {

          @Override
          public Map<String, String> mapRow(ResultSet rs, int rowNum) throws SQLException {
             return Map.of(
              "username", rs.getString("staging_src_username"),
              "password", rs.getString("staging_src_pwd"),
              "port", rs.getString("staging_src_port"),
              "dbName", rs.getString("staging_db_name"),
              "host", rs.getString("staging_src_host")

             );
            
          }

          
        });
        
  }


}

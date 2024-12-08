package com.example.datawarehousDBStaging.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.datawarehousDBStaging.config.ControllerDataSourceConfig;

@Service
public class ControllerService {

  @Autowired
  private JdbcTemplate controllerJdbcTemplate;

  @Autowired
  private ControllerDataSourceConfig controllerDataSourceConfig;

  public Map<String, String> getStagingDBInfo() {
    return controllerDataSourceConfig.getStagingDBInfo(controllerJdbcTemplate);
  }

  public List<Map<String, Object>> getActiveFileConfigs() {
    String sql = "SELECT * FROM file_config WHERE status = 'active'";
    return controllerJdbcTemplate.queryForList(sql);
  }

  public List<Map<String, Object>> getFileLogs(String fileName) {
    String sql = "SELECT * FROM file_log WHERE name_file = ? AND event_type = 'load_to_staging' AND status = 'completed'";
    return controllerJdbcTemplate.queryForList(sql, fileName);
  }

  public Map<String, Object> getConfig() {
    String sql = "SELECT * FROM configs WHERE status = 'active'";
    return controllerJdbcTemplate.queryForMap(sql);
  }

}

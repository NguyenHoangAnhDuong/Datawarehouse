package com.example.datawarehousDBStaging.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.example.datawarehousDBStaging.entity.FileLog;

@Service
public class FileLogService {
  @Autowired
  private ControllerService controllerService;

  public List<FileLog> getFileLogs(String nameFile) {
    String sql = "SELECT * FROM file_log WHERE name_file LIKE ? AND event_type = 'load_to_staging' AND status = 'completed'";
    return controllerService.getJdbcTemplate().query(sql, ps -> ps.setString(1, nameFile + "%"),
        new BeanPropertyRowMapper<>(FileLog.class));
  }
}

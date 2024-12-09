package com.example.datawarehousDBStaging.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.example.datawarehousDBStaging.entity.FileConfig;

@Service
public class FileConfigService {

  @Autowired
  private ControllerService controllerService;

  public List<FileConfig> getActiveFileConfigs(Integer configId) {
    String sql = "SELECT * FROM file_config WHERE id_config = ? AND status = 'active'";
    return controllerService.getJdbcTemplate().query(sql, ps -> ps.setInt(1, configId),
        new BeanPropertyRowMapper<>(FileConfig.class));
  }
}

package com.example.datawarehousDBStaging.service;

import java.util.List;

import com.example.datawarehousDBStaging.repository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.datawarehousDBStaging.entity.Config;

@Service
public class ConfigService {

  @Autowired
  private ControllerService controllerService;

  public List<Config> getActiveConfigs() {
    String sql = "SELECT * FROM public.configs;";
    return controllerService.getJdbcTemplate().query(sql, new BeanPropertyRowMapper<>(Config.class));
  }

  public void insertConfig(Config config) {
    String sql = "INSERT INTO public.configs (" +
            "staging_src_username, staging_src_pwd, staging_src_port, staging_db_name, staging_src_host, " +
            "wh_src_username, wh_src_pwd, wh_src_port, wh_db_name, wh_src_host, " +
            "mart_src_username, mart_src_pwd, mart_src_port, mart_db_name, mart_src_host, " +
            "download_path, error_to_mail, flag, status" +
            ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    JdbcTemplate jdbcTemplate = controllerService.getJdbcTemplate();
    jdbcTemplate.update(sql,
            config.getStgSrcUsername(), config.getStgSrcPwd(), config.getStgSrcPort(),
            config.getStgDbName(), config.getStgSrcHost(), config.getWhSrcUsername(), config.getWhSrcPwd(),
            config.getWhSrcPort(), config.getWhDbName(), config.getWhSrcHost(), config.getMartSrcUsername(),
            config.getMartSrcPwd(), config.getMartSrcPort(), config.getMartDbName(), config.getMartSrcHost(),
            config.getDownloadPath(), config.getErrorToMail(), config.getFlag(), config.getStatus());
  }
}
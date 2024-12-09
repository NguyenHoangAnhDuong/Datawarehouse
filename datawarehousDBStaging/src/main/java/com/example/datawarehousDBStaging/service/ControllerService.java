package com.example.datawarehousDBStaging.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ControllerService {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public JdbcTemplate getJdbcTemplate() {
    return jdbcTemplate;
  }
}

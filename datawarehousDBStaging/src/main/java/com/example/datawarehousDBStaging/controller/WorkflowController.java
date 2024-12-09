package com.example.datawarehousDBStaging.controller;

import com.example.datawarehousDBStaging.entity.Config;
import com.example.datawarehousDBStaging.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.datawarehousDBStaging.service.EmailService;
import com.example.datawarehousDBStaging.service.WorkflowService;

import java.util.List;

@RestController
@RequestMapping("/load-to-staging")
public class WorkflowController {

  @Autowired
  private WorkflowService workflowService;

  @Autowired
  private ConfigService configService;

  @GetMapping("/execute")
  public String executeWorkflow() {
    workflowService.executeWorkflow();
    return "Workflow executed.";
  }

  @GetMapping("/config")
  public List<Config> getConfigs() {
    System.out.println("du lieu ne: " + configService.getActiveConfigs());
    return configService.getActiveConfigs();
  }

  @GetMapping("/insert")
  public void insertConfig() {
    Config cfg = new Config();
    cfg.setStgSrcUsername("xinchao");
    cfg.setStgSrcPwd("hihi");
    cfg.setStgSrcPort(5555);
    cfg.setStgDbName("okok");
    cfg.setStgSrcHost("cauchuyen");
    cfg.setWhSrcUsername("lolamne");
    cfg.setWhSrcPwd("warehouse_password");
    cfg.setWhSrcPort(6666);
    cfg.setWhDbName("warehouse_db");
    cfg.setWhSrcHost("localhost");
    cfg.setMartSrcUsername("mart_user");
    cfg.setMartSrcPwd("mart_password");
    cfg.setMartSrcPort(9999);
    cfg.setMartDbName("mart_db");
    cfg.setMartSrcHost("localhost");
    cfg.setDownloadPath("/downloads/path");
    cfg.setErrorToMail("daloxayemtugiaji@gmail.com");
    cfg.setFlag("hihi");
    cfg.setStatus("ualasaota1");

    // Log the configuration being inserted
    System.out.println("Inserting config: " + cfg);

    configService.insertConfig(cfg);
  }

}

package com.example.datawarehousDBStaging.controller;

import com.example.datawarehousDBStaging.service.StagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staging")
public class StagingController {

  @Autowired
  private StagingService stagingService;

  @GetMapping("/process")
  public String processStaging() {
    stagingService.processStaging();
    return "Staging process initiated.";
  }
}
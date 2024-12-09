package com.example.datawarehousDBStaging.controller;

import com.example.datawarehousDBStaging.service.CheckLoadData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoadToStagingController {

    @Autowired
    private CheckLoadData loadToStagingService;

    @GetMapping("/load-to-staging")
    public String loadToStaging() {
        try {
            loadToStagingService.loadDataToStaging();
            return "Data load to staging completed successfully.";
        } catch (Exception e) {
            return "Error during data load to staging: " + e.getMessage();
        }
    }
}

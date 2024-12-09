package com.example.scrapeDatafromWeb.controller;


import com.example.scrapeDatafromWeb.entity.ConfigEntity;
import com.example.scrapeDatafromWeb.request.ApiResponse;
import com.example.scrapeDatafromWeb.request.ConfigsRequest;
import com.example.scrapeDatafromWeb.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class ConfigController {
    @Autowired
    private ConfigService configService;

    @PostMapping
    public ApiResponse<ConfigEntity> getConfig(@RequestBody ConfigsRequest request){
        ApiResponse response = configService.saveConfig(request);
        return response;
    }
}

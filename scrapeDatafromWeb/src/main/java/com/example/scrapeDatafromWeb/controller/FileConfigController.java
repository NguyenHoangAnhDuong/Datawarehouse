package com.example.scrapeDatafromWeb.controller;

import com.example.scrapeDatafromWeb.entity.FileConfigEntity;
import com.example.scrapeDatafromWeb.request.ApiResponse;
import com.example.scrapeDatafromWeb.request.FileConfigRequest;
import com.example.scrapeDatafromWeb.service.FileConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/file-config")
public class FileConfigController {

    @Autowired
    private FileConfigService fileConfigService;

    @PostMapping
    public ApiResponse<List<FileConfigEntity>> saveFileConfig(@RequestBody List<FileConfigRequest> requests) {
        ApiResponse<List<FileConfigEntity>> response = fileConfigService.saveFileConfig(requests);
        return response;
    }
}

package com.example.datawarehousDBStaging.service;

import com.example.datawarehousDBStaging.entity.Config;
import com.example.datawarehousDBStaging.entity.FileConfig;
import com.example.datawarehousDBStaging.repository.ApiResponse;
import com.example.datawarehousDBStaging.repository.FileConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileConfigService {
    @Autowired
    private FileConfigRepository fileConfigRepository;

    @Autowired
    private ConfigService configService;



    public     List<FileConfig>  getActiveFileConfigs(){

        ApiResponse<Config> configApiResponse = configService.getActiveConfig();

        List<FileConfig> entity = fileConfigRepository.findByConfigEntityIdAndStatus(configApiResponse.getData().getId(),"active");

        return entity;
    }


}

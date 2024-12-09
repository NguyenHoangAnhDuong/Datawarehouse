package com.example.scrapeDatafromWeb.service;

import com.example.scrapeDatafromWeb.entity.ConfigEntity;
import com.example.scrapeDatafromWeb.entity.FileConfigEntity;
import com.example.scrapeDatafromWeb.repository.ConfigRepository;

import com.example.scrapeDatafromWeb.request.ApiResponse;
import com.example.scrapeDatafromWeb.request.ConfigsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigService {

    @Autowired
    private ConfigRepository configRepository;

    private ApiResponse<ConfigEntity> updateConfigStatus(String status, String flag) {
        ApiResponse<ConfigEntity> response = new ApiResponse<>();
        try{
            List<ConfigEntity> configEntityList = configRepository.findAll();

            for(ConfigEntity configEntity : configEntityList){
                configEntity.setStatus(status);
                configEntity.setFlag(flag);
            }
            List<ConfigEntity> updatedConfigList = configRepository.saveAll(configEntityList);
            response.setStatus("success");
            response.setMessage("Config updated successfully");
        }
        catch (Exception e) {
            response.setStatus("error");
            response.setMessage(e.getMessage());
        }


        return response;
    }


    public ApiResponse<ConfigEntity> saveConfig(ConfigsRequest request) {
        ApiResponse<ConfigEntity> response = new ApiResponse<>();
        ApiResponse<ConfigEntity> updateResponse = updateConfigStatus("inactive", "0");
        if (updateResponse.getStatus().equals("error")) {
            return updateResponse;
        }

        try{

            ConfigEntity configEntity = new ConfigEntity().builder()
                    .stagingDbName(request.getStagingDbName())
                    .stagingSourceHost(request.getStagingSourceHost())
                    .stagingSourcePort(request.getStagingSourcePort())
                    .stagingSourceUsername(request.getStagingSourceUsername())
                    .stagingSourcePassword(request.getStagingSourcePassword())
                    .whDbName(request.getWhDbName())
                    .whSourceHost(request.getWhSourceHost())
                    .whSourcePort(request.getWhSourcePort())
                    .whSourceUsername(request.getWhSourceUsername())
                    .whSourcePassword(request.getWhSourcePassword())
                    .martDbName(request.getMartDbName())
                    .martSourceHost(request.getMartSourceHost())
                    .martSourcePort(request.getMartSourcePort())
                    .martSourceUsername(request.getMartSourceUsername())
                    .martSourcePassword(request.getMartSourcePassword())
                    .downloadPath(request.getDownloadPath())
                    .errorToEmail(request.getErrorToEmail())
                    .flag(request.getFlag())
                    .status(request.getStatus())
                    .build();
            ConfigEntity savedConfig = configRepository.save(configEntity);
            response.setStatus("success");
            response.setMessage("Config saved successfully");
            response.setData(savedConfig);
            System.out.println(response);
            System.out.println("Config saved successfully");


        }catch (Exception e){
            response.setStatus("error");
            response.setMessage(e.getMessage());
        }
        return  response;
    }

    public List<ConfigEntity> getAllConfigsWithStatus(String status){
        return configRepository.findAllByStatus(status);
    }
}

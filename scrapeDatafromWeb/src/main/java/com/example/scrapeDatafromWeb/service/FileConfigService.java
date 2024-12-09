package com.example.scrapeDatafromWeb.service;

import com.example.scrapeDatafromWeb.entity.ConfigEntity;
import com.example.scrapeDatafromWeb.entity.FileConfigEntity;
import com.example.scrapeDatafromWeb.repository.FileConfigRepository;
import com.example.scrapeDatafromWeb.request.ApiResponse;
import com.example.scrapeDatafromWeb.request.FileConfigRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileConfigService {

    @Autowired
    private FileConfigRepository fileConfigRepository;

    @Autowired
    private ConfigService configService;

    private ApiResponse<List<FileConfigEntity>> updateStatus(String status, String event) {
        ApiResponse<List<FileConfigEntity>> response = new ApiResponse<>();
        try {
            List<FileConfigEntity> fileConfigEntities = fileConfigRepository.findAllByStatusAndEvent(status, event);


            for (FileConfigEntity fileConfigEntity : fileConfigEntities) {
                fileConfigEntity.setStatus("inactive");
                fileConfigRepository.save(fileConfigEntity);
            }

            response.setStatus("SUCCESS");
            response.setMessage("File Config status updated successfully");
        } catch (Exception e) {
            response.setStatus("ERROR");
            response.setMessage("Failed to update File Config status: " + e.getMessage());
        }
        return response;
    }

    public ApiResponse<List<FileConfigEntity>> saveFileConfig(List<FileConfigRequest> requests) {
        ApiResponse<List<FileConfigEntity>> response = new ApiResponse<>();
        List<FileConfigEntity> listSave = new ArrayList<>();

        try {
            // Update the status of existing configs to inactive
            ApiResponse<List<FileConfigEntity>> updateResponse = updateStatus("active", "scrape");
            if ("ERROR".equals(updateResponse.getStatus())) {
                return updateResponse;
            }

            // Fetch active configurations
            List<ConfigEntity> configEntities = configService.getAllConfigsWithStatus("active");
            if (configEntities.isEmpty()) {
                response.setStatus("ERROR");
                response.setMessage("No active config found");
                return response;
            }

            String filePath = configEntities.get(0).getDownloadPath();

            // Process each request
            for (FileConfigRequest fileConfigRequest : requests) {
                FileConfigEntity fileConfigEntity = FileConfigEntity.builder()
                        .configEntity(configEntities.get(0))
                        .createdAt(LocalDateTime.now())
                        .createdBy(fileConfigRequest.getCreatedBy())
                        .deleted(false)
                        .event(fileConfigRequest.getEvent())
                        .fileColumnList(fileConfigRequest.getFileColumnList())
                        .fileTimestamp(LocalDateTime.now())
                        .name(fileConfigRequest.getName())
                        .path(filePath + File.separator + fileConfigRequest.getName())
                        .status("active")
                        .build();

                fileConfigRepository.save(fileConfigEntity);
                listSave.add(fileConfigEntity);
            }

            response.setStatus("SUCCESS");
            response.setMessage("File Config created successfully");
            response.setData(listSave);
        } catch (Exception e) {

            response.setStatus("ERROR");
            response.setMessage("Failed to save File Config: " + e.getMessage());
        }

        return response;
    }
}

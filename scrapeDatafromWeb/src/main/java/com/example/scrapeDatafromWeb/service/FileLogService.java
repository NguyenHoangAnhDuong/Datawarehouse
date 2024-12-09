package com.example.scrapeDatafromWeb.service;


import com.example.scrapeDatafromWeb.entity.FileLogsEntity;
import com.example.scrapeDatafromWeb.repository.FileLogRepository;
import com.example.scrapeDatafromWeb.request.ApiResponse;
import com.example.scrapeDatafromWeb.request.FileLogRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FileLogService {

    @Autowired
    private FileLogRepository fileLogRepository;

    public ApiResponse<FileLogsEntity> saveFileLogs(FileLogRequest request){
        ApiResponse<FileLogsEntity> response = new ApiResponse<>();
        try{
            FileLogsEntity fileLogsEntity = FileLogsEntity.builder()
                    .createdAt(LocalDateTime.now())
                    .name(request.getName())
                    .eventType(request.getEventType())
                    .status(request.getStatus())
                    .build();

            FileLogsEntity savedFileLogs = fileLogRepository.save(fileLogsEntity);
            response.setStatus("SUCCESS");
            response.setMessage("File Logs saved successfully");
            response.setData(savedFileLogs);
        }catch (Exception e){
            response.setStatus("ERROR");
            response.setMessage("Failed to save File Logs: " + e.getMessage());
        }

        return response;
    }
}
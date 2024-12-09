package com.example.datawarehouse.service;

import com.example.datawarehouse.entity.ConfigEntity;
import com.example.datawarehouse.request.ConfigRequest;
import com.example.datawarehouse.response.ApiResponse;
import com.example.datawarehouse.response.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigService {
    @Autowired
    private ConfigRepository configRepository;

    public ApiResponse<ConfigEntity> saveConfig(ConfigRequest request) {
       ApiResponse<ConfigEntity> response = new ApiResponse<>();

       try {
           ConfigEntity configEntity = new ConfigEntity().builder()
                   .status(request.getStatus())
                   .stagingSrcUsername(request.getStagingSrcUsername())
                   .stagingSrcPwd(request.getStagingSrcPwd())
                   .stagingSrcPort(request.getStagingSrcPort())
                   .stagingDbName(request.getStagingDbName())
                   .stagingSrcHost(request.getStagingSrcHost())
                   .whSrcUsername(request.getWhSrcUsername())
                   .whSrcPwd(request.getWhSrcPwd())
                   .whSrcPort(request.getWhSrcPort())
                   .whDbName(request.getWhDbName())
                   .whSrcHost(request.getWhSrcHost())
                   .martSrcUsername(request.getMartSrcUsername())
                   .martSrcPwd(request.getMartSrcPwd())
                   .martDbName(request.getMartDbName())
                   .martSrcHost(request.getMartSrcHost())
                   .downloadPath(request.getDownloadPath())
                   .errorToMail(request.getErrorToMail())
                   .flag(request.getFlag())
                   .build();

       }catch (Exception e) {
           response.setStatus("error");
           response.setMessage(e.getMessage());
       }

       return response;
    }

}

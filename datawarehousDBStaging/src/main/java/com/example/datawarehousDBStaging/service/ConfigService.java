package com.example.datawarehousDBStaging.service;

import com.example.datawarehousDBStaging.entity.Config;
import com.example.datawarehousDBStaging.repository.ApiResponse;
import com.example.datawarehousDBStaging.repository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigService {

    @Autowired
    private ConfigRepository configRepository;

    public ApiResponse<Config> getActiveConfig() {
        ApiResponse<Config> response = new ApiResponse<>();
       try {
           Config config = configRepository.findByStatus("active");
           if(config != null) {
               response.setData(config);
               response.setMessage("Successfully");
               response.setStatus("Success");
           }
           response.setStatus("Failed");
           response.setMessage("Null");
       }
       catch (Exception e) {
           response.setStatus("Failed");
           response.setMessage("Failly");
       }
        return response;
    }


}

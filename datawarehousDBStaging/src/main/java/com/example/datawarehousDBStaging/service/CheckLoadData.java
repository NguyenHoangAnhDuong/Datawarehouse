package com.example.datawarehousDBStaging.service;

import com.example.datawarehousDBStaging.entity.Config;
import com.example.datawarehousDBStaging.entity.FileConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CheckLoadData {

    @Autowired
    private ConfigService configService;

    @Autowired
    private FileConfigService fileConfigService;

    @Autowired
    private FileLogService fileLogService;

    @Autowired
    private EmailService emailService;

    @Autowired
    @Qualifier("controllerJdbcTemplate")
    private JdbcTemplate controllerJdbcTemplate;

    @Autowired
    @Qualifier("stagingJdbcTemplate")
    private JdbcTemplate stagingJdbcTemplate;

    public void loadDataToStaging() {
        boolean checkLoadData = fileLogService.isFileLoadedSuccessfully();

        if (checkLoadData) {
            emailService.sendEmail(configService.getActiveConfig().getData().getErrorToEmail());
        } else {
            emailService.sendErrorEmail(configService.getActiveConfig().getData().getErrorToEmail(), "False.");
        }
    }

    private void loadFileDataToStaging(String nameFile) {
        // Code để load dữ liệu từ file vào StagingDB
        // Cần tùy thuộc vào cấu trúc dữ liệu và quy trình xử lý file cụ thể
    }
}

package com.example.datawarehousDBStaging.service;

import com.example.datawarehousDBStaging.entity.Config;
import com.example.datawarehousDBStaging.entity.FileConfig;
import com.example.datawarehousDBStaging.entity.FileLog;
import com.example.datawarehousDBStaging.repository.ApiResponse;
import com.example.datawarehousDBStaging.repository.FileLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FileLogService {
    @Autowired
    private FileLogRepository fileLogRepository;

    @Autowired
    private FileConfigService fileConfigService;


    private List<String> formatPathName() {

        List<String> pathNameList = new ArrayList<>();

        List<FileConfig> fileConfigs = fileConfigService.getActiveFileConfigs() ;

        String dateNow = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

        for(FileConfig fileConfig : fileConfigs){
            String path = fileConfig.getPath() + dateNow + ".csv";
            pathNameList.add(path);
        }

        return pathNameList;
    }



    public boolean isFileLoadedSuccessfully() {
        boolean allFileLoadSuccess = false;

        List<FileLog> fileLogList = fileLogRepository.findAll();

        List<String> pathNameList = formatPathName();

        for(FileLog fileLog : fileLogList){

            for(String pathName : pathNameList){
                if(fileLog.getName().equals(pathName) && fileLog.getEventType().equals("load-to-staging") && fileLog.getStatus().equals("completed")){

                    allFileLoadSuccess = true;



                }

                allFileLoadSuccess = false;




            }


        }

        return allFileLoadSuccess;



    }
}

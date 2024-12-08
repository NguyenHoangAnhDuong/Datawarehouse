package com.example.datawarehouse.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfigRequest {
    private Number status;
    private String stagingSrcUsername;
    private String stagingSrcPwd;
    private String stagingSrcPort;
    private String stagingDbName;
    private String stagingSrcHost;
    private String whSrcUsername;
    private String whSrcPwd;
    private String whSrcPort;
    private String whDbName;
    private String whSrcHost;
    private String martSrcUsername;
    private String martSrcPwd;
    private String martDbName;
    private String martSrcHost;
    private String downloadPath;
    private String errorToMail;
    private String flag;



}

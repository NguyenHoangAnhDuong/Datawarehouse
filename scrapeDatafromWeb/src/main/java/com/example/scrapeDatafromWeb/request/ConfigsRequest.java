package com.example.scrapeDatafromWeb.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfigsRequest {
    private String stagingDbName;
    private String stagingSourceHost;

    private int stagingSourcePort;
    private String stagingSourceUsername;
    private String stagingSourcePassword;
    private String whDbName;
    private String whSourceHost;

    private int whSourcePort;
    private String whSourceUsername;
    private String whSourcePassword;

    private String martDbName;
    private String martSourceHost;
    private int martSourcePort;
    private String martSourceUsername;
    private String martSourcePassword;

    private String downloadPath;
    private String errorToEmail;
    private String flag;
    private String status;




}

package com.example.scrapeDatafromWeb.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileConfigRequest {
    private String name;
    private LocalDateTime fileTimestamp;
    private String path;
    private LocalDateTime createdAt;
    private String fileColumnList;
    private String createdBy;
    private boolean deleted;
    private String status;
    private String event;


}

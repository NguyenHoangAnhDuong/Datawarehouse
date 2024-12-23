package com.example.scrapeDatafromWeb.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileLogRequest {
    private String name;
    private String eventType;
    private String status;

}

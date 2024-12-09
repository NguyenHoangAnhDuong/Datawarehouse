package com.example.datawarehouseDBwarehouse.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LogUtil {
    private static final Logger logger = LoggerFactory.getLogger(LogUtil.class);

    public void logEvent(String eventType, String message) {
        // Ghi log vào hệ thống (có thể mở rộng để ghi vào database)
        logger.info("Event Type: {}, Message: {}", eventType, message);
    }
}

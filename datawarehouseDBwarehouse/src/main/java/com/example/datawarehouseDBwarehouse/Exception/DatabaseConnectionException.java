package com.example.datawarehouseDBwarehouse.Exception;

public class DatabaseConnectionException extends RuntimeException {

    // Constructor nhận thông điệp lỗi
    public DatabaseConnectionException(String message) {
        super(message);
    }

    // Constructor nhận thông điệp lỗi và nguyên nhân
    public DatabaseConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}

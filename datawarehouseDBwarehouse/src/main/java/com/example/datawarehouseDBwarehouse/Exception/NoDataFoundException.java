package com.example.datawarehouseDBwarehouse.Exception;

public class NoDataFoundException extends RuntimeException {

    // Constructor nhận thông điệp lỗi
    public NoDataFoundException(String message) {
        super(message);
    }
}

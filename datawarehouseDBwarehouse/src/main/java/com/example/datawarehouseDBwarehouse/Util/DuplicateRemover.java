package com.example.datawarehouseDBwarehouse.Util;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Component
public class DuplicateRemover {
    // Loại bỏ các bản ghi trùng lặp trong danh sách
    public static <T> List<T> removeDuplicates(List<T> dataList) {
        Set<T> set = new HashSet<>(dataList);
        return set.stream().collect(Collectors.toList());
    }
}

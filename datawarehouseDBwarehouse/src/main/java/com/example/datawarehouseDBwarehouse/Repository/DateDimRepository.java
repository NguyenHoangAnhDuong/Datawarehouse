package com.example.datawarehouseDBwarehouse.Repository;

import com.example.datawarehouseDBwarehouse.Model.DateDim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface DateDimRepository extends JpaRepository<DateDim, Long> {

    // Chỉnh lại tên phương thức để phù hợp với thuộc tính 'fullDate'
    DateDim findByFullDate(LocalDate fullDate);
}

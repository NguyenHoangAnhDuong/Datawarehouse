package com.example.datawarehouseDBwarehouse.Repository;

import com.example.datawarehouseDBwarehouse.Model.SourceDim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface SourceDimRepository extends JpaRepository<SourceDim, Integer> {
    SourceDim findBySource(String source);
}
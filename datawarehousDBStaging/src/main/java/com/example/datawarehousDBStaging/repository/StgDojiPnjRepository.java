package com.example.datawarehousDBStaging.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.datawarehousDBStaging.entity.StgDojiPnj;

@Repository
public interface StgDojiPnjRepository extends JpaRepository<StgDojiPnj, Integer> {
}
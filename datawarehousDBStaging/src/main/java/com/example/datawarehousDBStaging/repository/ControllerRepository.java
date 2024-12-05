package com.example.datawarehousDBStaging.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.datawarehousDBStaging.entity.Config;

@Repository
public interface ControllerRepository  extends JpaRepository<Config, Integer> {


  

}

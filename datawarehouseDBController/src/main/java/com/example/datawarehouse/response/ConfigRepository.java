package com.example.datawarehouse.response;

import com.example.datawarehouse.entity.ConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigRepository  extends JpaRepository<ConfigEntity, Number> {

}

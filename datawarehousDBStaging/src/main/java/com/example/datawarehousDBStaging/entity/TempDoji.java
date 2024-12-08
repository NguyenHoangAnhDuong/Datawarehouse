package com.example.datawarehousDBStaging.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "temp_doji")
@Getter
@Setter
public class TempDoji {

  @Id
  private String id;
  private String type_name;
  private String region;
  private String buy_value;
  private String sell_value;
  private String source;

}

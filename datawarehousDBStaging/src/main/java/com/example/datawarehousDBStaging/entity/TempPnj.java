package com.example.datawarehousDBStaging.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "temp_pnj")
@Getter
@Setter
public class TempPnj {

  @Id
  private String id;
  private String type_name;
  private String region;
  private String buy_value;
  private String sell_value;
  private String source;

}
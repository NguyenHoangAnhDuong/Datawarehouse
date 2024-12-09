package com.example.datawarehousDBStaging.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "stg_doji_pnj")
@Getter
@Setter
public class StgDojiPnj {

  @Id
  private int id;
  private String type_name;
  private String region;
  private double buy_value;
  private double sell_value;
  private String source;

}
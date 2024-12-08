package com.example.datawarehousDBStaging.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
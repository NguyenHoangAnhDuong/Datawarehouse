package com.example.datawarehousDBStaging.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "stg_sjc")
@Getter
@Setter
public class StgSjc {

  @Id
  private int id;
  private String type_name;
  private String region;
  private double buy_value;
  private double sell_value;
  private double buy_differ_value;
  private String buy_differ;
  private double sell_differ_value;
  private String sell_differ;

}

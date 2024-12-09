package com.example.datawarehousDBStaging.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "temp_sjc")
@Getter
@Setter
public class TempSjc {

  @Id
  private String id;
  private String type_name;
  private String region;
  private String buy;
  private String buy_value;
  private String sell;
  private String sell_value;
  private String source;

}
package com.example.datawarehouseDBwarehouse.Model;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "stg_doji_pnj") // hoặc đổi thành stg_sjc nếu cần
public class    StagingData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_name", nullable = false)
    private String typeName;

    @Column(name = "region", nullable = false)
    private String region;

    @Column(name = "buy_value", nullable = false)
    private Double buyValue;

    @Column(name = "sell_value", nullable = false)
    private Double sellValue;

    @Column(name = "source", nullable = false)
    private String source;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Double getBuyValue() {
        return buyValue;
    }

    public void setBuyValue(Double buyValue) {
        this.buyValue = buyValue;
    }

    public Double getSellValue() {
        return sellValue;
    }

    public void setSellValue(Double sellValue) {
        this.sellValue = sellValue;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

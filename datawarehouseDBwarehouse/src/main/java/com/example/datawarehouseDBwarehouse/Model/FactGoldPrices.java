package com.example.datawarehouseDBwarehouse.Model;
import jakarta.persistence.*;

@Entity
@Table(name = "fact_gold_prices")
public class FactGoldPrices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_date")
    private DateDim date;

    @ManyToOne
    @JoinColumn(name = "id_source")
    private SourceDim source;

    @Column(name = "type_name")
    private String typeName;

    @Column(name = "region")
    private String region;

    @Column(name = "buy_value")
    private Double buyValue;

    @Column(name = "sell_value")
    private Double sellValue;

    @Column(name = "comparison_buy")
    private Double comparisonBuy;

    @Column(name = "comparison_sell")
    private Double comparisonSell;

    @Column(name = "percentage_buy")
    private Double percentageBuy;

    @Column(name = "percentage_sell")
    private Double percentageSell;

    // Getters and Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SourceDim getSource() {
        return source;
    }

    public void setSource(SourceDim source) {
        this.source = source;
    }

    public DateDim getDate() {
        return date;
    }

    public void setDate(DateDim date) {
        this.date = date;
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

    public Double getSellValue() {
        return sellValue;
    }

    public void setSellValue(Double sellValue) {
        this.sellValue = sellValue;
    }

    public Double getBuyValue() {
        return buyValue;
    }

    public void setBuyValue(Double buyValue) {
        this.buyValue = buyValue;
    }

    public Double getComparisonBuy() {
        return comparisonBuy;
    }

    public void setComparisonBuy(Double comparisonBuy) {
        this.comparisonBuy = comparisonBuy;
    }

    public Double getPercentageBuy() {
        return percentageBuy;
    }

    public void setPercentageBuy(Double percentageBuy) {
        this.percentageBuy = percentageBuy;
    }

    public Double getComparisonSell() {
        return comparisonSell;
    }

    public void setComparisonSell(Double comparisonSell) {
        this.comparisonSell = comparisonSell;
    }

    public Double getPercentageSell() {
        return percentageSell;
    }

    public void setPercentageSell(Double percentageSell) {
        this.percentageSell = percentageSell;
    }
}

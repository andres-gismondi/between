package com.between.between.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "prices")
public class Price {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer priceList;
    private Integer productId;
    private BigDecimal price;
    private Integer priority;
    @Column(name = "curr")
    private String currency;

    public Price clone(Integer priceList) {
        Price nPrice = new Price();
        nPrice.setPriority(this.getPriority());
        nPrice.setPrice(this.getPrice().multiply(BigDecimal.valueOf(priceList)));
        nPrice.setPriceList(this.getPriceList());
        nPrice.setCurrency(this.getCurrency());
        nPrice.setBrand(this.getBrand());
        nPrice.setProductId(this.getProductId());
        nPrice.setStartDate(this.getStartDate());
        nPrice.setEndDate(this.getEndDate());
        return nPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Integer getPriceList() {
        return priceList;
    }

    public void setPriceList(Integer priceList) {
        this.priceList = priceList;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}

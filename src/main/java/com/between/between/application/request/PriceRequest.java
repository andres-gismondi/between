package com.between.between.application.request;

import java.time.LocalDateTime;

public class PriceRequest {

    private LocalDateTime startDate;
    private Integer productId;
    private Long brandId;

    public PriceRequest(LocalDateTime startDate, Integer productId, Long brandId) {
        this.startDate = startDate;
        this.productId = productId;
        this.brandId = brandId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }
}

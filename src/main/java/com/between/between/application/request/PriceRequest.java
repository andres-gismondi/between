package com.between.between.application.request;


import java.sql.Date;

public class PriceRequest {

    private Date startDate;
    private Integer productId;
    private Long brandId;

    public PriceRequest(Date startDate, Integer productId, Long brandId) {
        this.startDate = startDate;
        this.productId = productId;
        this.brandId = brandId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
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

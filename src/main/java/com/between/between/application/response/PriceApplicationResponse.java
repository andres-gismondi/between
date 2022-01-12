package com.between.between.application.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PriceApplicationResponse {

    private LocalDateTime startApplicationDate;
    private LocalDateTime endApplicationDate;
    private Integer priceList;
    private Integer productId;
    private BigDecimal price;

    public PriceApplicationResponse(PriceApplicationBuilder builder) {
        this.startApplicationDate = builder.startApplicationDate;
        this.endApplicationDate = builder.endApplicationDate;
        this.priceList = builder.priceList;
        this.productId = builder.productId;
        this.price = builder.price;
    }

    public LocalDateTime getEndApplicationDate() {
        return endApplicationDate;
    }

    public void setEndApplicationDate(LocalDateTime endApplicationDate) {
        this.endApplicationDate = endApplicationDate;
    }

    public LocalDateTime getStartApplicationDate() {
        return startApplicationDate;
    }

    public void setStartApplicationDate(LocalDateTime startApplicationDate) {
        this.startApplicationDate = startApplicationDate;
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

    public static class PriceApplicationBuilder {
        private LocalDateTime startApplicationDate;
        private LocalDateTime endApplicationDate;
        private Integer priceList;
        private Integer productId;
        private BigDecimal price;

        public PriceApplicationBuilder endApplicationDate(LocalDateTime date) {
            this.endApplicationDate = date;
            return this;
        }

        public PriceApplicationBuilder startApplicationDate(LocalDateTime date) {
            this.startApplicationDate = date;
            return this;
        }

        public PriceApplicationBuilder priceList(Integer priceList) {
            this.priceList = priceList;
            return this;
        }

        public PriceApplicationBuilder productId(Integer productId) {
            this.productId = productId;
            return this;
        }

        public PriceApplicationBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public PriceApplicationResponse build() {
            return new PriceApplicationResponse(this);
        }
    }

}

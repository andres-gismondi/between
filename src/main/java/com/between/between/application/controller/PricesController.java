package com.between.between.application.controller;

import com.between.between.application.request.PriceRequest;
import com.between.between.application.response.PriceResponse;
import com.between.between.domain.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/prices")
public class PricesController {

    @Autowired
    private PriceService priceService;

    @GetMapping
    public ResponseEntity<List<PriceResponse>> getPrices(@RequestParam(name = "start_date") Date startdate,
                                                         @RequestParam(name = "product_id") Integer productId,
                                                         @RequestParam(name = "brand_id") Long brandId) {
        List<PriceResponse> response = this.priceService.getPrices(new PriceRequest(startdate,productId,brandId));
        return ResponseEntity.ok(response);
    }

}

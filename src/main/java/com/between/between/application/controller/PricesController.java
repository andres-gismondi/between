package com.between.between.application.controller;

import com.between.between.application.request.PriceRequest;
import com.between.between.domain.entities.Price;
import com.between.between.domain.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/prices")
public class PricesController {

    @Autowired
    private PriceService priceService;

    @GetMapping
    public ResponseEntity<List<Price>> getPrices(PriceRequest request) {
        List<Price> response = this.priceService.getPrices();
        return ResponseEntity.ok(response);
    }

}

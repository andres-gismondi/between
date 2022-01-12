package com.between.between.application.controller;

import com.between.between.application.request.PriceRequest;
import com.between.between.application.response.PriceResponse;
import com.between.between.application.utils.DateUtils;
import com.between.between.domain.service.PriceService;
import com.between.between.domain.service.impl.PriceServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "/prices")
public class PricesController {

    private static final Logger logger = LoggerFactory.getLogger(PricesController.class);

    @Autowired
    private PriceService priceService;

    @GetMapping
    public ResponseEntity<List<PriceResponse>> getPrices(@RequestParam(name = "start_date") String startDate,
                                                         @RequestParam(name = "product_id") Integer productId,
                                                         @RequestParam(name = "brand_id") Long brandId) {
        logger.info("Getting prices with: [start_date_:{}]", startDate);
        LocalDateTime startDateFormatted = DateUtils.dateFormatted(startDate);
        List<PriceResponse> response = this.priceService.getPrices(new PriceRequest(startDateFormatted,productId,brandId));
        return ResponseEntity.ok(response);
    }

}

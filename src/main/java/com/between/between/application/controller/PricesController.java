package com.between.between.application.controller;

import com.between.between.application.exceptions.PriceServiceException;
import com.between.between.application.request.PriceRequest;
import com.between.between.application.response.PriceApplicationResponse;
import com.between.between.application.utils.DateUtils;
import com.between.between.domain.service.PriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/prices")
public class PricesController {

    private static final Logger logger = LoggerFactory.getLogger(PricesController.class);

    @Autowired
    private PriceService priceService;

    @GetMapping()
    public ResponseEntity<PriceApplicationResponse> getPrices(@RequestParam(name = "start_date") String startDate,
                                                              @RequestParam(name = "product_id") Integer productId,
                                                              @RequestParam(name = "brand_id") Long brandId) throws PriceServiceException {
        logger.info("Getting prices with: [start_date_:{}]", startDate);
        LocalDateTime startDateFormatted = DateUtils.dateFormatted(startDate);
        PriceApplicationResponse response = this.priceService.getTotalPrice(new PriceRequest(startDateFormatted,productId,brandId));
        return ResponseEntity.ok(response);
    }

}

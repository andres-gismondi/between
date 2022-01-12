package com.between.between.domain.service.impl;

import com.between.between.application.request.PriceRequest;
import com.between.between.application.response.PriceApplicationResponse;
import com.between.between.domain.entities.Price;
import com.between.between.domain.repository.PriceRepository;
import com.between.between.domain.service.PriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceServiceImpl implements PriceService {

    private static final Logger logger = LoggerFactory.getLogger(PriceServiceImpl.class);

    private PriceRepository priceRepository;

    @Autowired
    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public PriceApplicationResponse getTotalPrice(PriceRequest request) {
        logger.info("Calculating pricing with [start_date:{}], [product_id:{}] and [brand_id:{}]", request.getStartDate(), request.getProductId(), request.getBrandId());
        List<Price> repositoryResponse = this.priceRepository.findPrices(request.getStartDate(), request.getProductId(), request.getBrandId());
        return this.setTotalApplication(repositoryResponse, request.getProductId());
    }

    private PriceApplicationResponse setTotalApplication(List<Price> prices, Integer productId) {
        PriceApplicationResponse.PriceApplicationBuilder builder = new PriceApplicationResponse.PriceApplicationBuilder();

        builder = this.setDates(prices, builder);
        Integer priceList = this.setPriceList(prices, builder);

        builder = this.setPrice(prices, priceList, builder);
        builder.productId(productId);

        return builder.build();
    }

    private PriceApplicationResponse.PriceApplicationBuilder setDates(List<Price> prices, PriceApplicationResponse.PriceApplicationBuilder builder) {
        LocalDateTime startDate = prices.stream()
                .min(Comparator.comparing(Price::getStartDate))
                .get()
                .getStartDate();

        LocalDateTime endDate = prices.stream()
                .min(Comparator.comparing(Price::getEndDate))
                .get()
                .getEndDate();

        logger.info("Setting dates with [start_date:{}] and [end_date:{}]", startDate, endDate);
        return builder.startApplicationDate(startDate)
                .endApplicationDate(endDate);
    }

    private Integer setPriceList(List<Price> prices, PriceApplicationResponse.PriceApplicationBuilder builder) {
        Integer priceList = prices.stream()
                .max(Comparator.comparing(Price::getPriority))
                .get()
                .getPriceList();

        logger.info("Setting price_list: {}", priceList);
        builder.priceList(priceList);
        return priceList;
    }

    private PriceApplicationResponse.PriceApplicationBuilder setPrice(List<Price> prices, Integer priceList, PriceApplicationResponse.PriceApplicationBuilder builder) {
        BigDecimal totalValues = prices.stream()
                .map(v -> v.clone(priceList))
                .map(Price::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        logger.info("Setting total_value:{}", totalValues);
        return builder.price(totalValues);
    }

}

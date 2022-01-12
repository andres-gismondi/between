package com.between.between.domain.service.impl;

import com.between.between.application.request.PriceRequest;
import com.between.between.application.response.PriceResponse;
import com.between.between.domain.entities.Price;
import com.between.between.domain.mapper.PricesMapper;
import com.between.between.domain.repository.PriceRepository;
import com.between.between.domain.service.PriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {

    private static final Logger logger = LoggerFactory.getLogger(PriceServiceImpl.class);

    private PriceRepository priceRepository;
    private PricesMapper pricesMapper;

    @Autowired
    public PriceServiceImpl(PriceRepository priceRepository, PricesMapper pricesMapper) {
        this.priceRepository = priceRepository;
        this.pricesMapper = pricesMapper;
    }

    @Override
    public List<PriceResponse> getPrices(PriceRequest request) {
        logger.info("Getting prices with: [start_date:{}],[product_id:{}] and [brand_id:{}]", request.getStartDate(), request.getProductId(), request.getBrandId());
        List<Price> repositoryResponse = this.priceRepository.findPrices(request.getStartDate(), request.getProductId(), request.getBrandId());
        return this.pricesMapper.mapToPricesResponse(repositoryResponse);
    }
}

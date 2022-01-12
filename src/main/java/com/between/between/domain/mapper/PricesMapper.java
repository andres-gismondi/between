package com.between.between.domain.mapper;

import com.between.between.application.response.PriceResponse;
import com.between.between.domain.entities.Price;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PricesMapper {

    public List<PriceResponse> mapToPricesResponse(List<Price> prices) {
        return prices.stream().map(this::mapToPriceResponse).collect(Collectors.toList());
    }

    private PriceResponse mapToPriceResponse(Price price) {
        PriceResponse response = new PriceResponse();
        response.setPrice(price.getPrice());
        response.setProductId(price.getProductId());
        response.setPriceList(price.getPriceList());
        response.setCurrency(price.getCurrency());
        response.setStartDate(price.getStartDate());
        response.setEndDate(price.getEndDate());
        return  response;
    }

}

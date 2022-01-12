package com.between.between.domain.service;

import com.between.between.application.request.PriceRequest;
import com.between.between.application.response.PriceResponse;

import java.util.List;

public interface PriceService {

    List<PriceResponse> getPrices(PriceRequest request);

}

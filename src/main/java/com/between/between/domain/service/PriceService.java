package com.between.between.domain.service;

import com.between.between.application.request.PriceRequest;
import com.between.between.application.response.PriceApplicationResponse;

public interface PriceService {

    PriceApplicationResponse getTotalPrice(PriceRequest request);

}

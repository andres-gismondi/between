package com.between.between.domain.repository;

import com.between.between.domain.entities.Price;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface PriceRepository {

    List<Price> findPrices(LocalDateTime startDate, Integer productId, Long brandId);

}

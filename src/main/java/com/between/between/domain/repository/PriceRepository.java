package com.between.between.domain.repository;

import com.between.between.domain.entities.Price;

import java.sql.Date;
import java.util.List;

public interface PriceRepository {

    List<Price> findPrices(Date startDate, Integer productId, Long brandId);

}

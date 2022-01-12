package com.between.between.domain.service.impl;

import com.between.between.domain.entities.Price;
import com.between.between.domain.repository.PriceRepository;
import com.between.between.domain.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {

    private PriceRepository priceRepository;

    @Autowired
    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public List<Price> getPrices() {
        return null;
    }
}

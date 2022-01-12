package com.between.between.infraestructure.repository.mysql;

import com.between.between.domain.entities.Price;
import com.between.between.domain.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Primary
public class MySQLDBRepository implements PriceRepository {

    private JPAMySQLDBRepository jpaMySQLDBRepository;

    @Autowired
    public MySQLDBRepository(JPAMySQLDBRepository jpaMySQLDBRepository) {
        this.jpaMySQLDBRepository = jpaMySQLDBRepository;
    }

    @Override
    public List<Price> findPrices(LocalDateTime startDate, Integer productId, Long brandId) {
        return this.jpaMySQLDBRepository.findAllByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(productId, brandId, startDate, startDate);
    }
}

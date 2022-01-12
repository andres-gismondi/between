package com.between.between.infraestructure.repository.mysql;

import com.between.between.domain.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface JPAMySQLDBRepository extends JpaRepository<Price, Long> {

    List<Price> findAllByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(Integer productId, Long brandId, LocalDateTime startDate, LocalDateTime startDate2);

    List<Price> findAllByStartDateGreaterThan(LocalDateTime startDate);
}

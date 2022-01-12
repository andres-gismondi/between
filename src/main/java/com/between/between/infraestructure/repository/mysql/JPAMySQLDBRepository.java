package com.between.between.infraestructure.repository.mysql;

import com.between.between.domain.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface JPAMySQLDBRepository extends JpaRepository<List<Price>, Long> {

    List<Price> findAllByStartDateAndProductIdAndBrandId(Date startDate, Integer productId, Long brandId);

}

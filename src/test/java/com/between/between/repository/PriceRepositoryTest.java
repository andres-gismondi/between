package com.between.between.repository;

import com.between.between.BetweenApplication;
import com.between.between.domain.entities.Price;
import com.between.between.domain.repository.PriceRepository;
import com.between.between.infraestructure.repository.mysql.JPAMySQLDBRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = BetweenApplication.class)
public class PriceRepositoryTest {

    @Autowired
    private JPAMySQLDBRepository jpaMySQLDBRepository;
    @Autowired
    private PriceRepository repository;

    @Test
    public void success_with_one_element() {
        LocalDateTime date = LocalDateTime.of(2020,6,14,10,0);
        List<Price> response = this.repository.findPrices(date, 35455, 1L);

        Assert.assertEquals(1, response.size());
    }

    @Test
    public void success_with_two_element() {
        LocalDateTime date = LocalDateTime.of(2020,6,14,16,0);
        List<Price> response = this.repository.findPrices(date, 35455, 1L);

        Assert.assertEquals(2, response.size());
    }

    @Test
    public void should_return_empty_elements() {
        LocalDateTime date = LocalDateTime.of(2020,6,10,16,0);
        List<Price> response = this.repository.findPrices(date, 35455, 1L);

        Assert.assertEquals(0, response.size());
    }

}

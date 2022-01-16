package com.between.between.service;

import com.between.between.application.exceptions.PriceServiceException;
import com.between.between.application.request.PriceRequest;
import com.between.between.application.response.PriceApplicationResponse;
import com.between.between.domain.entities.Brand;
import com.between.between.domain.entities.Price;
import com.between.between.domain.repository.PriceRepository;
import com.between.between.domain.service.PriceService;
import com.between.between.domain.service.impl.PriceServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PriceServiceTest {

    private PriceRepository priceRepository;
    private PriceService service;

    @Before
    public void setUp() {
        this.priceRepository = mock(PriceRepository.class);
        this.service = new PriceServiceImpl(this.priceRepository);
    }

    @Test
    public void success_one_product_between_dates() throws PriceServiceException {
        LocalDateTime startDate = LocalDateTime.of(2022,1,10,0,0);
        LocalDateTime endDate = LocalDateTime.of(2022,1,10,10,0);
        Price price = this.buildPrice(1L, startDate, endDate,1,1);
        when(this.priceRepository.findPrices(any(),any(),any())).thenReturn(List.of(price));

        PriceApplicationResponse response = this.service.getTotalPrice(new PriceRequest(LocalDateTime.of(2022,1,10,8,0),1,1L));
        Assert.assertEquals(startDate, response.getStartApplicationDate());
        Assert.assertEquals(endDate, response.getEndApplicationDate());
        Assert.assertEquals(Integer.valueOf(1), response.getProductId());
        Assert.assertEquals(BigDecimal.valueOf(10.00).setScale(2, RoundingMode.CEILING), response.getPrice());
    }

    @Test
    public void success_two_product_between_dates() throws PriceServiceException {
        LocalDateTime startDate1 = LocalDateTime.of(2022,1,10,0,0);
        LocalDateTime endDate1 = LocalDateTime.of(2022,1,15,10,0);
        Price price1 = this.buildPrice(1L, startDate1, endDate1,1,1);

        LocalDateTime startDate2 = LocalDateTime.of(2022,1,11,0,0);
        LocalDateTime endDate2 = LocalDateTime.of(2022,1,14,10,0);
        Price price2 = this.buildPrice(2L, startDate2, endDate2,1,1);

        when(this.priceRepository.findPrices(any(),any(),any())).thenReturn(List.of(price1, price2));

        PriceApplicationResponse response = this.service.getTotalPrice(new PriceRequest(LocalDateTime.of(2022,1,12,8,0),1,1L));
        Assert.assertEquals(startDate2, response.getStartApplicationDate());
        Assert.assertEquals(endDate2, response.getEndApplicationDate());
        Assert.assertEquals(Integer.valueOf(1), response.getProductId());
        Assert.assertEquals(BigDecimal.valueOf(20.00).setScale(2, RoundingMode.CEILING), response.getPrice());
    }

    @Test
    public void success_mayor_priority() throws PriceServiceException {
        LocalDateTime startDate1 = LocalDateTime.of(2022,1,10,0,0);
        LocalDateTime endDate1 = LocalDateTime.of(2022,1,15,10,0);
        Price price1 = this.buildPrice(1L, startDate1, endDate1,1,1);

        LocalDateTime startDate2 = LocalDateTime.of(2022,1,11,0,0);
        LocalDateTime endDate2 = LocalDateTime.of(2022,1,14,10,0);
        Price price2 = this.buildPrice(2L, startDate2, endDate2,2,2);

        when(this.priceRepository.findPrices(any(),any(),any())).thenReturn(List.of(price1, price2));

        PriceApplicationResponse response = this.service.getTotalPrice(new PriceRequest(LocalDateTime.of(2022,1,12,8,0),1,1L));
        Assert.assertEquals(startDate2, response.getStartApplicationDate());
        Assert.assertEquals(endDate2, response.getEndApplicationDate());
        Assert.assertEquals(Integer.valueOf(1), response.getProductId());
        Assert.assertEquals(BigDecimal.valueOf(40.00).setScale(2, RoundingMode.CEILING), response.getPrice());
    }

    @Test
    public void should_return_empty_object_with_empty_repository_return() throws PriceServiceException {
        when(this.priceRepository.findPrices(any(),any(),any())).thenReturn(List.of());
        PriceApplicationResponse response = this.service.getTotalPrice(new PriceRequest(LocalDateTime.of(2022,1,12,8,0),1,1L));

        Assert.assertNull(response.getEndApplicationDate());
        Assert.assertNull(response.getEndApplicationDate());
        Assert.assertNull(response.getPrice());
        Assert.assertNull(response.getProductId());
    }

    @Test(expected = PriceServiceException.class)
    public void should_return_exception_with_repository_error() throws PriceServiceException {
        when(this.priceRepository.findPrices(any(),any(),any())).thenThrow(new RuntimeException("DB not found"));
        this.service.getTotalPrice(new PriceRequest(LocalDateTime.of(2022,1,12,8,0),1,1L));
    }

    private Price buildPrice(Long id, LocalDateTime startDate, LocalDateTime endDate, Integer priority, Integer priceList) {
        Price price = new Price();
        price.setId(id);
        price.setStartDate(startDate);
        price.setEndDate(endDate);
        price.setPrice(BigDecimal.valueOf(10.00));
        price.setProductId(1);
        price.setPriceList(priceList);
        price.setPriority(priority);
        Brand brand = new Brand();
        brand.setId(1L);
        brand.setDescription("Boots");
        price.setBrand(brand);
        return price;
    }

}

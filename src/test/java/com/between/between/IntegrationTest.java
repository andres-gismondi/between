package com.between.between;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = BetweenApplication.class
)
public class IntegrationTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void test_date_14_hour_10() throws Exception {
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get("/prices")
                .param("start_date","2020-06-14 10:00:00")
                .param("product_id","35455")
                .param("brand_id","1")
                .accept(MimeTypeUtils.APPLICATION_JSON_VALUE));
        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.start_application_date").value("2020-06-14T00:00:00"));
        result.andExpect(jsonPath("$.end_application_date").value("2020-12-31T23:59:59"));
        result.andExpect(jsonPath("$.price_list").value("1"));
        result.andExpect(jsonPath("$.product_id").value("35455"));
        result.andExpect(jsonPath("$.price").value("35.50"));
    }

    @Test
    public void test_date_14_hour_16() throws Exception {
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get("/prices")
                .param("start_date","2020-06-14 16:00:00")
                .param("product_id","35455")
                .param("brand_id","1")
                .accept(MimeTypeUtils.APPLICATION_JSON_VALUE));
        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.start_application_date").value("2020-06-14T15:00:00"));
        result.andExpect(jsonPath("$.end_application_date").value("2020-06-14T18:30:00"));
        result.andExpect(jsonPath("$.price_list").value("1"));
        result.andExpect(jsonPath("$.product_id").value("35455"));
        result.andExpect(jsonPath("$.price").value("60.95"));
    }

    @Test
    public void test_date_14_hour_21() throws Exception {
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get("/prices")
                .param("start_date","2020-06-14 21:00:00")
                .param("product_id","35455")
                .param("brand_id","1")
                .accept(MimeTypeUtils.APPLICATION_JSON_VALUE));
        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.start_application_date").value("2020-06-14T00:00:00"));
        result.andExpect(jsonPath("$.end_application_date").value("2020-12-31T23:59:59"));
        result.andExpect(jsonPath("$.price_list").value("1"));
        result.andExpect(jsonPath("$.product_id").value("35455"));
        result.andExpect(jsonPath("$.price").value("35.5"));
    }

    @Test
    public void test_date_15_hour_10() throws Exception {
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get("/prices")
                .param("start_date","2020-06-15 10:00:00")
                .param("product_id","35455")
                .param("brand_id","1")
                .accept(MimeTypeUtils.APPLICATION_JSON_VALUE));
        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.start_application_date").value("2020-06-15T00:00:00"));
        result.andExpect(jsonPath("$.end_application_date").value("2020-06-15T11:00:00"));
        result.andExpect(jsonPath("$.price_list").value("1"));
        result.andExpect(jsonPath("$.product_id").value("35455"));
        result.andExpect(jsonPath("$.price").value("66.0"));
    }

    @Test
    public void test_date_16_hour_21() throws Exception {
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get("/prices")
                .param("start_date","2020-06-15 21:00:00")
                .param("product_id","35455")
                .param("brand_id","1")
                .accept(MimeTypeUtils.APPLICATION_JSON_VALUE));
        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.start_application_date").value("2020-06-15T16:00:00"));
        result.andExpect(jsonPath("$.end_application_date").value("2020-12-31T23:59:59"));
        result.andExpect(jsonPath("$.price_list").value("1"));
        result.andExpect(jsonPath("$.product_id").value("35455"));
        result.andExpect(jsonPath("$.price").value("74.45"));
    }

}

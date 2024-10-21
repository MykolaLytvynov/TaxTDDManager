package ua.mykola.taxtddmanager.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ua.mykola.taxtddmanager.service.TaxService;

import java.math.BigDecimal;

@WebMvcTest(TaxController.class)
class TaxControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaxService taxService;

    @Test
    @DisplayName("Calculating tax with correct params")
    void shouldReturnCalculatedTax() throws Exception {
        int income = 10;
        int negativeMonths = 5;
        BigDecimal expectedResult = BigDecimal.valueOf(15);

        Mockito.when(taxService.calculateTax(income, negativeMonths))
                .thenReturn(expectedResult);

        mockMvc.perform(MockMvcRequestBuilders.get("/tax/getTax")
                    .param("income", String.valueOf(income))
                    .param("months", String.valueOf(negativeMonths)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf(expectedResult)));
    }

    @Test
    @DisplayName("Calculating tax when months is negative")
    void shouldReturnBadRequestWhenMonthsIsNegative() throws Exception {
        int income = 10;
        int negativeMonths = -3;
        String errorMessage = "Months should be greater than 0";

        mockMvc.perform(MockMvcRequestBuilders.get("/tax/getTax")
                    .param("income", String.valueOf(income))
                    .param("months", String.valueOf(negativeMonths)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string(errorMessage));
    }

    @Test
    @DisplayName("Calculating tax when income is negative")
    void shouldReturnBadRequestWhenIncomeIsNegative() throws Exception {
        int income = -10;
        int negativeMonths = 3;
        String errorMessage = "Income should be greater than 0";

        mockMvc.perform(MockMvcRequestBuilders.get("/tax/getTax")
                        .param("income", String.valueOf(income))
                        .param("months", String.valueOf(negativeMonths)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string(errorMessage));
    }
}

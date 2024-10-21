package ua.mykola.taxtddmanager.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TaxService {
    private static final BigDecimal TAX_PERCENTAGE = BigDecimal.valueOf(0.30);

    public BigDecimal calculateTax(int income, int months) {

        return BigDecimal.valueOf(income)
                .multiply(BigDecimal.valueOf(months))
                .multiply(TAX_PERCENTAGE)
                .setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}

package ua.mykola.taxtddmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.mykola.taxtddmanager.exception.ValidateException;
import ua.mykola.taxtddmanager.service.TaxService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/tax")
public class TaxController {
    private final TaxService taxService;

    @Autowired
    public TaxController(TaxService taxService) {
        this.taxService = taxService;
    }

    @GetMapping("/getTax")
    public ResponseEntity<BigDecimal> calculateTax(@RequestParam int income,
                                                   @RequestParam int months) {
        if (income < 0) {
            throw new ValidateException("Income should be greater than 0");
        }
        if (months < 0) {
            throw new ValidateException("Months should be greater than 0");
        }

        BigDecimal tax = taxService.calculateTax(income, months);
        return ResponseEntity.ok().body(tax);
    }
}

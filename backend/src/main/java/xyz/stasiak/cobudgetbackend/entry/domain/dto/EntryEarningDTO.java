package xyz.stasiak.cobudgetbackend.entry.domain.dto;

import xyz.stasiak.cobudgetbackend.validation.CheckDateFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public record EntryEarningDTO(@CheckDateFormat(pattern = "uuuu-MM-dd", message = "date is invalid") String date,

                              @DecimalMin(value = "0.0", message = "amount can't be negative") @Digits(integer = 15, fraction = 2, message = "amount can have max 2 decimal places") BigDecimal amount,

                              @NotBlank(message = "category is mandatory") String category,

                              String comment) {

}

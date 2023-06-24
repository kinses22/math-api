package com.sojern.mathapi.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class MathRequestDTO {

    @NotNull
    @Min(value = 1, message = "Quantifier must be greater than 0")
    @Max(value = 100, message = "Quantifier must be equal or less than 100")
    private final Integer quantifier;

    @NotNull
    @NotEmpty(message = "List of numbers can't be empty")
    private final List<Double> numbers;

    public MathRequestDTO(final Integer quantifier, final List<Double> numbers) {
        this.quantifier = quantifier;
        this.numbers = numbers;
    }

    public int getQuantifier() {
        return quantifier;
    }

    public List<Double> getNumbers() {
        return numbers;
    }
}

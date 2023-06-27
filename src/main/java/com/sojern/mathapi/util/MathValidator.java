package com.sojern.mathapi.util;

import com.sojern.mathapi.exception.ArrayListContainsNullValueException;
import com.sojern.mathapi.exception.ArrayListSizeException;
import com.sojern.mathapi.exception.DoublesMoreThanTwoDigitsException;
import com.sojern.mathapi.exception.InvalidQuantifierException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Util class for validations
 *
 * @author Stephen Kinsella
 */
@Component
public class MathValidator {

    public static final String QUANTIFIER_LESS_THAN_1_OR_GREATER_THAN_100 = " Percentile quantifier should not be less than 1 or greater than 100";
    public static final String QUANTIFIER_LESS_THAN_ONE_OR_EQUAL_OR_GREATER_THAN_LIST_SIZE = "Quantifier should not be equal or greater than list size";
    public static final String GREATER_THAN_1_AND_LESS_THAN_OR_EQUAL_TO_100 = "List size must be greater than 1 and less than or equal to 100";
    public static final String CONTAINS_NUMBERS_WITH_MORE_THAN_TWO_DECIMAL_POINTS = "Array contains numbers with more than two decimal points";
    public static final String NULL_VALUES_IN_THE_LIST = "There was null values in the list";
    public static final int ONE = 1;
    public static final int ONE_HUNDRED = 100;
    public static final String DOT = "\\.";
    public static final int TWO = 2;

    /**
     * Validates a percentile quantifier provided.
     * @param quantifier a quantifier to decide how many to bring back.
     * @throws InvalidQuantifierException if the quantifier is less than 1 or greater than 100
     */
    public void validatePercentileQuantifier(final int quantifier) {

        if (quantifier < ONE || quantifier > ONE_HUNDRED) {
            throw new InvalidQuantifierException(QUANTIFIER_LESS_THAN_1_OR_GREATER_THAN_100);
        }
    }

    /**
     * Validates a quantifier provided.
     * @param quantifier a quantifier to decide how many to bring back.
     * @throws InvalidQuantifierException if the quantifier is less than 1 or greater than 100
     */
    public void validateQuantifier(final int quantifier, final List<Double> numbers) {

        if (numbers.size() <= quantifier || quantifier < ONE) {
            throw new InvalidQuantifierException(QUANTIFIER_LESS_THAN_ONE_OR_EQUAL_OR_GREATER_THAN_LIST_SIZE);
        }
    }

    /**
     * Validates list size
     * @param numbers a list of doubles.
     * @throws ArrayListSizeException if the list size is less than two or greater than or equal to 100
     */
    public void validateArraySize(final List<Double> numbers) {

        if (numbers.size() <= ONE || numbers.size() >= ONE_HUNDRED) {
            throw new ArrayListSizeException(GREATER_THAN_1_AND_LESS_THAN_OR_EQUAL_TO_100);
        }
    }

    /**
     * Filters a list for non-null values and sorts if instructed.
     * @param numbers a list of doubles.
     * @param sort a sort boolean.
     * @return list of doubles
     */
    public List<Double> filterForNullValues(final List<Double> numbers, boolean sort) {

        List<Double> nonNullList = sort ? numbers.stream()
                .filter(Objects::nonNull).sorted()
                .collect(Collectors.toList()) : numbers.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        checkNullValueInArray(numbers, nonNullList);
        return nonNullList;
    }

    /**
     * Validates decimal places in numbers in a list
     * @param numbers a list of doubles.
     * @throws DoublesMoreThanTwoDigitsException if a number in the list contains more than 2 decimal places
     */
    public void hasNumberWithMoreThanTwoDecimals(final List<Double> numbers) {

        boolean hasMoreThanTwoDecimals = numbers.stream().anyMatch(
                n -> {
                    String decimalPoint = String.valueOf(n).split(DOT)[ONE];
                    return decimalPoint.length() > TWO;
                });
        if (hasMoreThanTwoDecimals) {
            throw new DoublesMoreThanTwoDigitsException(CONTAINS_NUMBERS_WITH_MORE_THAN_TWO_DECIMAL_POINTS);
        }
    }

    /**
     * checks to see if the original list is greater than the filtered list for non-null values
     * @param numbers a list of doubles.
     * @param nonNullNumbers a filtered list of doubles containing non-null values.
     * @throws ArrayListContainsNullValueException if the original list size is less than the filtered non-null list
     */
    private void checkNullValueInArray(List<Double> numbers,
                                       List<Double> nonNullNumbers) {
        if (nonNullNumbers.size() != numbers.size()) {
            throw new ArrayListContainsNullValueException(NULL_VALUES_IN_THE_LIST);
        }
    }

}

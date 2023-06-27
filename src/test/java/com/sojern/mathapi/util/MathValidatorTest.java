package com.sojern.mathapi.util;

import com.sojern.mathapi.exception.ArrayListContainsNullValueException;
import com.sojern.mathapi.exception.ArrayListSizeException;
import com.sojern.mathapi.exception.DoublesMoreThanTwoDigitsException;
import com.sojern.mathapi.exception.InvalidQuantifierException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class MathValidatorTest {

    MathValidator subjectUnderTest;
    List<Double> numbersList;

    @BeforeEach
    void setUp() {
        numbersList = new ArrayList<>();
        subjectUnderTest = new MathValidator();
    }

    @Test()
    public void testEmptyListThrowsEmptyListException() {
        assertThrows(ArrayListSizeException.class,
                () -> subjectUnderTest.validateArraySize(Collections.emptyList()));
    }

    @Test()
    public void testListWithOneValueThrowsListSizeException() {
        Collections.addAll(numbersList, 1.1);
        assertThrows(ArrayListSizeException.class,
                () -> subjectUnderTest.validateArraySize(numbersList));
    }

    @Test()
    public void testListWithNullValueThrowsNullPointer() {
        Collections.addAll(numbersList, 1.1, 2.1, null);

        assertThrows(ArrayListContainsNullValueException.class,
                () -> subjectUnderTest.filterForNullValues(numbersList, true));
    }

    @Test()
    public void testValidatePercentileQuantifier() {
        int quantifier = 101;

        InvalidQuantifierException exception = assertThrows(
                InvalidQuantifierException.class,
                () -> subjectUnderTest.validatePercentileQuantifier(quantifier)
        );
        Assertions.assertEquals(MathValidator.QUANTIFIER_LESS_THAN_1_OR_GREATER_THAN_100, exception.getMessage());
    }

    @Test()
    public void testValidatePercentileQuanitfierWithList() {
        int quantifier = 5;

        InvalidQuantifierException exception = assertThrows(
                InvalidQuantifierException.class,
                () -> subjectUnderTest.validateQuantifier(quantifier, List.of(1.0,2.0,3.0))
        );
        Assertions.assertEquals(MathValidator.QUANTIFIER_LESS_THAN_ONE_OR_EQUAL_OR_GREATER_THAN_LIST_SIZE, exception.getMessage());
    }

    @Test()
    public void testValidateListHasNumbersWithMoreThanTwoDigits() {
        DoublesMoreThanTwoDigitsException exception = assertThrows(
                DoublesMoreThanTwoDigitsException.class,
                () -> subjectUnderTest.hasNumberWithMoreThanTwoDecimals(List.of(1.123,2.0,3.0))
        );
        Assertions.assertEquals(MathValidator.CONTAINS_NUMBERS_WITH_MORE_THAN_TWO_DECIMAL_POINTS, exception.getMessage());
    }



}
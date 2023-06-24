package com.sojern.mathapi.service;

import com.sojern.mathapi.exception.ArrayListSizeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MathServiceImplTest {

    private MathService subjectUnderTest;

    @BeforeEach
    void setUp() {
        subjectUnderTest = new MathServiceImpl();
    }

    @Test()
    public void testEmptyListThrowsEmptyListException() {
        assertThrows(ArrayListSizeException.class,
                () -> subjectUnderTest.getMinimumNumberList(1, Collections.emptyList()));
    }

    @Test()
    public void testListWithNullValueThrowsNullPointer() {
        List<Double> numbersList = new ArrayList<>();
        Collections.addAll(numbersList, 1.1, 2.1, null);

        assertThrows(ArrayListSizeException.class,
                () -> subjectUnderTest.getMinimumNumberList(1, numbersList));
    }
}
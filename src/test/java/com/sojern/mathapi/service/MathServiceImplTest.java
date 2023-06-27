package com.sojern.mathapi.service;

import com.sojern.mathapi.util.MathValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class MathServiceImplTest {

    private MathService subjectUnderTest;

    @Mock
    private MathValidator mathValidator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        subjectUnderTest = new MathServiceImpl(mathValidator);
    }

    @Test
    public void testGetMinimumNumberList() {
        List<Double> numbers = Arrays.asList(1.0, 5.0, 3.0, 2.0, 4.0);
        when(mathValidator.filterForNullValues(numbers, true)).thenReturn(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0));

        List<Double> minResultList = subjectUnderTest.getMinimumNumberList(3, numbers);

        assertEquals(Arrays.asList(1.0, 2.0, 3.0), minResultList);
    }

    @Test
    public void testGetMaxNumberList() {
        List<Double> numbers = Arrays.asList(4.55, 5.55, 10.0, 5.66, 100.0);
        when(mathValidator.filterForNullValues(numbers, true)).thenReturn(Arrays.asList(4.55, 5.55, 5.66, 10.0, 100.0));

        List<Double> maximumNumberList = subjectUnderTest.getMaximumNumberList(1, numbers);

        assertEquals(List.of(100.0), maximumNumberList);
    }

    @Test
    public void testGetAverageNumber() {
        List<Double> numbers = Arrays.asList(60.0,70.0,80.0,90.0,100.0,10.0,20.0,30.0,40.0,50.0,0.00);
        when(mathValidator.filterForNullValues(numbers, false))
                .thenReturn(Arrays.asList(60.0,70.0,80.0,90.0,100.0,10.0,20.0,30.0,40.0,50.0,0.00));

        double average = subjectUnderTest.getAverageFromNumberList(numbers);

        assertEquals(50.0, average);
    }

    @Test
    public void testGetMedianNumber() {
        List<Double> numbers = Arrays.asList(40.0,50.0,20.0);
        when(mathValidator.filterForNullValues(numbers, true))
                .thenReturn(Arrays.asList(20.0,40.0,50.0));

        double median = subjectUnderTest.getMedianFromNumberList(numbers);

        assertEquals(40.0, median);
    }

    @Test
    public void testGetMedianNumberEvenNumberList() {
        List<Double> numbers = Arrays.asList(40.0,50.0,30.0,20.0);
        when(mathValidator.filterForNullValues(numbers, true))
                .thenReturn(Arrays.asList(20.0,30.0,40.0,50.0));

        double median = subjectUnderTest.getMedianFromNumberList(numbers);

        assertEquals(35.0, median);
    }

    @Test
    public void testGetPercentile() {
        List<Double> numbers = Arrays.asList(20.0,30.0,50.0,1.0,5.0,10.0,15.0);
        when(mathValidator.filterForNullValues(numbers, true))
                .thenReturn(Arrays.asList(1.0,5.0,10.0,15.0,20.0,30.0,50.0));

        double percentile = subjectUnderTest.getPercentileFromList(80, numbers);

        assertEquals(30.0, percentile);
    }

    @Test
    public void testGetPercentileTwo() {
        List<Double> numbers = Arrays.asList(1.0,5.5,10.0,15.5,20.0,30.5,50.5);
        when(mathValidator.filterForNullValues(numbers, true))
                .thenReturn(Arrays.asList( 1.0,5.5,10.0,15.5,20.0,30.5,50.5));

        double percentile = subjectUnderTest.getPercentileFromList(50, numbers);

        assertEquals(15.5, percentile);
    }
}
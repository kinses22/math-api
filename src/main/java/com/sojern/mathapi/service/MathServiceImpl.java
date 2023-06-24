package com.sojern.mathapi.service;

import com.sojern.mathapi.exception.ArrayListContainsNullValueException;
import com.sojern.mathapi.exception.ArrayListSizeException;
import com.sojern.mathapi.exception.DoublesMoreThanTwoDigitsException;
import com.sojern.mathapi.exception.InvalidQuantifierException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MathServiceImpl implements MathService {

    public static final int ZERO = 0;

    @Override
    public List<Double> getMinimumNumberList(int quantifier, List<Double> numbers) {

        validateArraySize(numbers);
        hasNumberWithMoreThanTwoDecimals(numbers);
        validateQuantifier(quantifier, numbers);
        List<Double> sortedNonNullList = filterForNullValuesAndSort(numbers);

        return sortedNonNullList.subList(ZERO, quantifier);
    }

    @Override
    public List<Double> getMaximumNumberList(int quantifier, List<Double> numbers) {

        validateArraySize(numbers);
        hasNumberWithMoreThanTwoDecimals(numbers);
        validateQuantifier(quantifier, numbers);
        List<Double> sortedNonNullList = filterForNullValuesAndSort(numbers);

        return sortedNonNullList.subList(numbers.size() - quantifier, numbers.size());
    }

    @Override
    public double getAverageFromNumberList(List<Double> numbers) {

        validateArraySize(numbers);
        hasNumberWithMoreThanTwoDecimals(numbers);
        List<Double> nonNullList = filterForNullValues(numbers);

        double sum = nonNullList.stream().mapToDouble(Double::doubleValue).sum();
        return sum / nonNullList.size();
    }

    @Override
    public double getMedianFromNumberList(List<Double> numbers) {

        validateArraySize(numbers);
        hasNumberWithMoreThanTwoDecimals(numbers);
        List<Double> sortedNonNullList = filterForNullValuesAndSort(numbers);

        double median;
        int middleIndex = numbers.size() / 2;
        if (sortedNonNullList.size() % 2 == 0) {
            median = (sortedNonNullList.get(middleIndex - 1) + sortedNonNullList.get(middleIndex)) / 2.0;
        } else {
            median = sortedNonNullList.get(middleIndex);
        }
        return median;
    }

    @Override
    public double getPercentileFromList(int quantifier, List<Double> numbers) {

        validateArraySize(numbers);
        hasNumberWithMoreThanTwoDecimals(numbers);
        List<Double> sortedNonNullList = filterForNullValuesAndSort(numbers);
        // Validate Qualifier

//        int index = (int) Math.round(sortedNonNullList.size() * (quantifier/100.0) + (100 - quantifier)/100.0) - 1;
//        return sortedNonNullList.get(index);


        double index = (quantifier/100.0) * sortedNonNullList.size() - 1;
        int lowerIndex = (int) Math.floor(index);
        int upperIndex = (int) Math.ceil(index);
        double differenceLower = index - lowerIndex;
        double differenceHigher = upperIndex - index;

        return numbers.get(differenceHigher > differenceLower? lowerIndex: upperIndex);

    }
    // 1,5,10,15,20,30,50 -> 80 : 30 (test)
    // 1,5.5,10,15.5,20,30.5,50.5 : 50 -> 15.5
    // 1,10,15,20 -> 84 - 15


    private void validateQuantifier(int quantifier, List<Double> numbers) {

        if (numbers.size() <= quantifier || quantifier < 1) {
            throw new InvalidQuantifierException("Quantifier should not be equal or greater than list size");
        }
    }

    private void validateArraySize(List<Double> numbers) {

        if (numbers.isEmpty() || numbers.size() > 20) {
            throw new ArrayListSizeException("List size: " + numbers.size() + " must be greater than 0 and less than or equal to 20");
        }
    }

    private List<Double> filterForNullValuesAndSort(List<Double> numbers) {

        List<Double> sortedNonNullList = numbers.stream()
                .filter(Objects::nonNull).sorted()
                .collect(Collectors.toList());

        checkNullValueInArray(numbers, sortedNonNullList);
        return sortedNonNullList;
    }

    private List<Double> filterForNullValues(List<Double> numbers) {

        List<Double> sortedNonNullList = numbers.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        checkNullValueInArray(numbers, sortedNonNullList);
        return sortedNonNullList;
    }

    private void checkNullValueInArray(List<Double> numbers,
                                       List<Double> sortedNonNullList) {
        if (sortedNonNullList.size() != numbers.size()) {
            throw new ArrayListContainsNullValueException("There was null values in the list");
        }
    }

    private void hasNumberWithMoreThanTwoDecimals(List<Double> numbers) {

        boolean hasMoreThanTwoDecimals = numbers.stream().anyMatch(
                n -> {
                    String decimalPoint = String.valueOf(n).split("\\.")[1];
                    return decimalPoint.length() > 2;
                });
        if (hasMoreThanTwoDecimals) {
            throw new DoublesMoreThanTwoDigitsException("Array contains numbers with more than two decimal points");
        }
    }
}

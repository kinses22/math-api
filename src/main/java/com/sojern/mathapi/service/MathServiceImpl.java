package com.sojern.mathapi.service;

import com.sojern.mathapi.util.MathValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for math operations listed below
 *
 * @author Stephen Kinsella
 */
@Service
public class MathServiceImpl implements MathService {

    public static final int ZERO = 0;
    public MathValidator mathValidator;

    @Autowired
    public MathServiceImpl(final MathValidator mathValidator) {
        this.mathValidator = mathValidator;
    }

    /**
     * Gets the minimum numbers from the given list based on a quantifier provided.
     * @param quantifier a quantifier to decide how many to bring back.
     * @param numbers containing a list of numbers.
     * @return List which contains a list of one or more values.
     */
    @Override
    public List<Double> getMinimumNumberList(int quantifier, List<Double> numbers) {

        mathValidator.validateQuantifier(quantifier, numbers);
        List<Double> sortedNonNullList = validateList(numbers, true);

        return sortedNonNullList.subList(ZERO, quantifier);
    }

    /**
     * Gets the maximum numbers from the given list based on a quantifier provided.
     * @param quantifier a quantifier to decide how many to bring back.
     * @param numbers containing a list of numbers.
     * @return List which contains a list of one or more values.
     */
    @Override
    public List<Double> getMaximumNumberList(int quantifier, List<Double> numbers) {

        mathValidator.validateQuantifier(quantifier, numbers);
        List<Double> sortedNonNullList = validateList(numbers, true);

        return sortedNonNullList.subList(numbers.size() - quantifier, numbers.size());
    }

    /**
     * Gets the average from the given list.
     * @param numbers containing a list of numbers.
     * @return an average value.
     */
    @Override
    public double getAverageFromNumberList(List<Double> numbers) {

        List<Double> nonNullList = validateList(numbers, false);

        double sum = nonNullList.stream().mapToDouble(Double::doubleValue).sum();
        return sum / nonNullList.size();
    }

    /**
     * Gets the median from the given list.
     * @param numbers containing a list of numbers.
     * @return a median value.
     */
    @Override
    public double getMedianFromNumberList(List<Double> numbers) {

        List<Double> sortedNonNullList = validateList(numbers, true);

        double median;
        int middleIndex = numbers.size() / 2;
        if (sortedNonNullList.size() % 2 == 0) {
            median = (sortedNonNullList.get(middleIndex - 1) + sortedNonNullList.get(middleIndex)) / 2.0;
        } else {
            median = sortedNonNullList.get(middleIndex);
        }
        return median;
    }

    /**
     * Gets the median from the given list and a quantifier.
     * @param quantifier a quantifier to decide the percentile.
     * @param numbers containing a list of numbers.
     * @return a percentile value.
     */
    @Override
    public double getPercentileFromList(int quantifier, List<Double> numbers) {

        mathValidator.validatePercentileQuantifier(quantifier);
        List<Double> sortedNonNullList = validateList(numbers, true);

        double index = (quantifier/100.0) * (sortedNonNullList.size() - 1);
        int lowerIndex = (int) Math.floor(index);
        int upperIndex = (int) Math.ceil(index);
        double differenceLower = index - lowerIndex;
        double differenceHigher = upperIndex - index;

        return sortedNonNullList.get(differenceHigher > differenceLower? lowerIndex: upperIndex);

    }

    /**
     * Validates the list using the MathValidator class
     * @param numbers a list of Doubles.
     * @param sort a boolean value.
     * @return a list of Doubles.
     */
    private List<Double> validateList(List<Double> numbers, boolean sort) {
        mathValidator.validateArraySize(numbers);
        List<Double> sortedNonNullList = mathValidator.filterForNullValues(numbers, sort);
        mathValidator.hasNumberWithMoreThanTwoDecimals(numbers);
        return sortedNonNullList;
    }
}

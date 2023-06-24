package com.sojern.mathapi.service;

import java.util.List;

public interface MathService {

    List<Double> getMinimumNumberList(final int quantifier, final List<Double> numbers);

    List<Double> getMaximumNumberList(final int quantifier, final List<Double> numbers);

    double getAverageFromNumberList(final List<Double> numbers);

    double getMedianFromNumberList(List<Double> numbers);

    double getPercentileFromList(int quantifier, List<Double> numbers);

}

package com.sojern.mathapi.controller;

import com.sojern.mathapi.domain.MathRequestDTO;
import com.sojern.mathapi.service.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller class for math operations listed below
 *
 * @author Stephen Kinsella
 */

@RestController
@RequestMapping("/api/v1/math")
public class MathController {

    final MathService mathService;

    @Autowired
    public MathController(final MathService mathService) {
        this.mathService = mathService;
    }


    /**
     * Gets the minimum numbers from the given list based on a quantifier provided.
     *
     * @param mathRequestDTO The MathRequestDTO containing a list of numbers and a quantifier.
     * @return ResponseEntity which contains a list of one or more values.
     */
    @PostMapping(value = "/min")
    public ResponseEntity<List<Double>> getMinNumberList(@Valid @RequestBody final MathRequestDTO mathRequestDTO) {
        return ResponseEntity.ok(mathService.getMinimumNumberList(mathRequestDTO.getQuantifier(), mathRequestDTO.getNumbers()));
    }

    /**
     * Gets the maximum numbers from the given list based on a quantifier provided.
     *
     * @param mathRequestDTO The MathRequestDTO containing a list of numbers and a quantifier.
     * @return ResponseEntity which contains a list of one or more values.
     */
    @PostMapping(value = "/max")
    public ResponseEntity<List<Double>> getMaxNumberList(@Valid @RequestBody final MathRequestDTO mathRequestDTO) {
        return ResponseEntity.ok(mathService.getMaximumNumberList(mathRequestDTO.getQuantifier(), mathRequestDTO.getNumbers()));
    }

    /**
     * Gets the average from the given list.
     *
     * @param numbers a list of numbers.
     * @return ResponseEntity which contains a number.
     */
    @PostMapping(value = "/avg")
    public ResponseEntity<Double> getAverageFromNumberList(@RequestBody final List<Double> numbers) {
        return ResponseEntity.ok(mathService.getAverageFromNumberList(numbers));
    }

    /**
     * Gets the median from the given list.
     *
     * @param numbers a list of numbers.
     * @return ResponseEntity which contains a number.
     */
    @PostMapping(value = "/median")
    public ResponseEntity<Double> getMedianFromNumberList(@RequestBody final List<Double> numbers) {
        return ResponseEntity.ok(mathService.getMedianFromNumberList(numbers));
    }

    /**
     * Gets the percentile from the given list and quantifier.
     *
     * @param mathRequestDTO The MathRequestDTO containing a list of numbers and a quantifier.
     * @return ResponseEntity which contains a number.
     */
    @PostMapping(value = "/percentile")
    public ResponseEntity<Double> getAverageFromNumberList(@Valid @RequestBody final MathRequestDTO mathRequestDTO) {
        return ResponseEntity.ok(mathService.getPercentileFromList(mathRequestDTO.getQuantifier(), mathRequestDTO.getNumbers()));
    }
}

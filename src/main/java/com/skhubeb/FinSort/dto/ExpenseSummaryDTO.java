package com.skhubeb.FinSort.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseSummaryDTO {

    private long totalCount;
    private double totalAmount;
    private double averageAmount;
    private Map<String, Double> categoryBreakdown;
}

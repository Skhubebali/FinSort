package com.skhubeb.FinSort.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "expenses")
public class ExpenseEntity {

    @Id
    private String id;

    private String title;

    private double amount;

    private Category category;

    private LocalDateTime dateTime;

    private String description;
}
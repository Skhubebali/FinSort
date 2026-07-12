package com.skhubeb.FinSort.controller;

import com.skhubeb.FinSort.dto.ExpenseSummaryDTO;
import com.skhubeb.FinSort.entity.Category;
import com.skhubeb.FinSort.entity.ExpenseEntity;
import com.skhubeb.FinSort.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/finsort")
@CrossOrigin(origins = "*")
public class ExpenseController {

    @Autowired
    private  ExpenseService expserv;


    @PostMapping("/expenses")
    public ResponseEntity<ExpenseEntity> createExpense(@RequestBody ExpenseEntity entity) {
        ExpenseEntity savedExpense = expserv.saveExpense(entity);
        return new ResponseEntity<>(savedExpense, HttpStatus.CREATED);
    }


    @GetMapping("/expenses")
    public ResponseEntity<List<ExpenseEntity>> getAllExpenses() {
        List<ExpenseEntity> expenses = expserv.getallExp();
        return ResponseEntity.ok(expenses);
    }


    @GetMapping("/expenses/{id}")
    public ResponseEntity<ExpenseEntity> getExpenseById(@PathVariable String id) {
        return expserv.getbyId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @PutMapping("/expenses/{id}")
    public ResponseEntity<ExpenseEntity> updateExpense(@PathVariable String id, @RequestBody ExpenseEntity updatedExpense) {
        return expserv.updateExpense(id, updatedExpense)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @DeleteMapping("/expenses/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable String id) {
        boolean deleted = expserv.deleteExpense(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}

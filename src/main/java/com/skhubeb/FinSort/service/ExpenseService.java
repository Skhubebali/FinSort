package com.skhubeb.FinSort.service;

import com.skhubeb.FinSort.dto.ExpenseSummaryDTO;
import com.skhubeb.FinSort.entity.Category;
import com.skhubeb.FinSort.entity.ExpenseEntity;
import com.skhubeb.FinSort.repository.ExpenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepo expenseRepo;

    public ExpenseEntity saveExpense(ExpenseEntity expentity) {
        if (expentity.getDateTime() == null) {
            expentity.setDateTime(LocalDateTime.now());
        }
        if (expentity.getCategory() == null) {
            expentity.setCategory(Category.OTHER);
        }
        return expenseRepo.save(expentity);
    }

    public List<ExpenseEntity> getallExp() {
        return expenseRepo.findAll();
    }

    public Optional<ExpenseEntity> getbyId(String id) {
        return expenseRepo.findById(id);
    }

    public Optional<ExpenseEntity> updateExpense(String id, ExpenseEntity updatedExpense) {
        return expenseRepo.findById(id).map(existing -> {
            if (updatedExpense.getTitle() != null && !updatedExpense.getTitle().isBlank()) {
                existing.setTitle(updatedExpense.getTitle());
            }
            if (updatedExpense.getAmount() >= 0) {
                existing.setAmount(updatedExpense.getAmount());
            }
            if (updatedExpense.getCategory() != null) {
                existing.setCategory(updatedExpense.getCategory());
            }
            if (updatedExpense.getDateTime() != null) {
                existing.setDateTime(updatedExpense.getDateTime());
            }
            if (updatedExpense.getDescription() != null) {
                existing.setDescription(updatedExpense.getDescription());
            }
            return expenseRepo.save(existing);
        });
    }

    public boolean deleteExpense(String id) {
        if (expenseRepo.existsById(id)) {
            expenseRepo.deleteById(id);
            return true;
        }
        return false;
    }

}

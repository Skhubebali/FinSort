package com.skhubeb.FinSort.service;

import com.skhubeb.FinSort.dto.ExpenseSummaryDTO;
import com.skhubeb.FinSort.entity.Category;
import com.skhubeb.FinSort.entity.ExpenseEntity;
import com.skhubeb.FinSort.repository.ExpenseRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExpenseServiceTest {

    @Mock
    private ExpenseRepo expenseRepo;

    @InjectMocks
    private ExpenseService expenseService;

    private ExpenseEntity expense1;
    private ExpenseEntity expense2;

    @BeforeEach
    void setUp() {
        expense1 = new ExpenseEntity("1", "Lunch", 15.50, Category.FOOD, LocalDateTime.now(), "Team lunch");
        expense2 = new ExpenseEntity("2", "Taxi", 25.00, Category.TRAVEL, LocalDateTime.now(), "Airport trip");
    }

    @Test
    void testSaveExpenseSetsDefaultCategoryAndDateWhenNull() {
        ExpenseEntity input = new ExpenseEntity();
        input.setTitle("Coffee");
        input.setAmount(4.50);

        when(expenseRepo.save(any(ExpenseEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ExpenseEntity saved = expenseService.saveExpense(input);

        assertNotNull(saved.getDateTime());
        assertEquals(Category.OTHER, saved.getCategory());
        assertEquals("Coffee", saved.getTitle());
    }

    @Test
    void testGetAllExp() {
        when(expenseRepo.findAllByOrderByDateTimeDesc()).thenReturn(Arrays.asList(expense1, expense2));

        List<ExpenseEntity> expenses = expenseService.getallExp();

        assertEquals(2, expenses.size());
        verify(expenseRepo, times(1)).findAllByOrderByDateTimeDesc();
    }

    @Test
    void testUpdateExpense() {
        ExpenseEntity updatedFields = new ExpenseEntity();
        updatedFields.setTitle("Dinner");
        updatedFields.setAmount(40.0);

        when(expenseRepo.findById("1")).thenReturn(Optional.of(expense1));
        when(expenseRepo.save(any(ExpenseEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Optional<ExpenseEntity> result = expenseService.updateExpense("1", updatedFields);

        assertTrue(result.isPresent());
        assertEquals("Dinner", result.get().getTitle());
        assertEquals(40.0, result.get().getAmount());
        assertEquals(Category.FOOD, result.get().getCategory());
    }

    @Test
    void testDeleteExpense() {
        when(expenseRepo.existsById("1")).thenReturn(true);
        doNothing().when(expenseRepo).deleteById("1");

        boolean deleted = expenseService.deleteExpense("1");

        assertTrue(deleted);
        verify(expenseRepo, times(1)).deleteById("1");
    }

    @Test
    void testGetExpenseSummary() {
        when(expenseRepo.findAll()).thenReturn(Arrays.asList(expense1, expense2));

        ExpenseSummaryDTO summary = expenseService.getExpenseSummary();

        assertEquals(2, summary.getTotalCount());
        assertEquals(40.50, summary.getTotalAmount(), 0.001);
        assertEquals(20.25, summary.getAverageAmount(), 0.001);
        assertEquals(15.50, summary.getCategoryBreakdown().get(Category.FOOD.name()), 0.001);
        assertEquals(25.00, summary.getCategoryBreakdown().get(Category.TRAVEL.name()), 0.001);
    }
}

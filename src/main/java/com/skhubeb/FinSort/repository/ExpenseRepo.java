package com.skhubeb.FinSort.repository;

import com.skhubeb.FinSort.entity.Category;
import com.skhubeb.FinSort.entity.ExpenseEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ExpenseRepo extends MongoRepository<ExpenseEntity, String> {


}

package com.skhubeb.FinSort.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection ="users")
public class User {
    @Id
    private String Id;

    @NonNull
    @Indexed( unique = true)
    private String uname;

    @NonNull
    private String password;

    @NonNull
    private String email;

    @DBRef
    private List<ExpenseEntity> myexpenses = new ArrayList<>();





}

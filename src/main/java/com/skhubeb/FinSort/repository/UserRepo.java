package com.skhubeb.FinSort.repository;

import com.skhubeb.FinSort.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<String, User> {

    User findbyUname(String uname);
}

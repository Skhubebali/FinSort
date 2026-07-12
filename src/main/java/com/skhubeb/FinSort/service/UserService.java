package com.skhubeb.FinSort.service;

import com.skhubeb.FinSort.entity.User;
import com.skhubeb.FinSort.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import javax.management.Query;

@Service
public class UserService {

   @Autowired
    private UserRepo userrepo;

   @Autowired
   private MongoTemplate mongoTemplate;

   public User createUser(User user){

   }
}

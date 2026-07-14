package com.skhubeb.FinSort.service;


import com.skhubeb.FinSort.entity.User;
import com.skhubeb.FinSort.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService {

   @Autowired
   public UserRepo userepo;



   private static final PasswordEncoder pwdencoder = new BCryptPasswordEncoder();

   public User createUser(User user){
      try {
         user.setPassword(pwdencoder.encode(user.getPassword()));
         return userepo.save(user);
      } catch (Exception e) {
         log.info("Error at {}",user.getUname(),e);
         throw new RuntimeException(e);
      }
   }
   public User saveUser(User user){
      return userepo.save(user);
   }

   public List<User> getAll(){

      return userepo.findAll();
   }

   public User getByuname(String id){

      return userepo.findByUname(id);
   }



   public User findByUname(String uname){
      return userepo.findByUname(uname);
   }

   public User update(User user){
      user.setPassword(pwdencoder.encode(user.getPassword()));
      return userepo.save(user);
   }
}

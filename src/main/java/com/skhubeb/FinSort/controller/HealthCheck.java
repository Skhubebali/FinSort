package com.skhubeb.FinSort.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("finsort")
public class HealthCheck {


  @GetMapping("health")
  public String hello(){
      System.out.println("All Ok");
      return "ALL OK !!";
  }



}

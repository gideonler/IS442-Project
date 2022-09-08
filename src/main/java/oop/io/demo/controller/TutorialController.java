package oop.io.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Controller can decide later
@RestController
@RequestMapping("/api/messages")
public class TutorialController {

      @GetMapping("/hello")
      public String hello(){
        return "Hi there";
      }
}

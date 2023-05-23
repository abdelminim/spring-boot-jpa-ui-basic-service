package com.clinic.appointment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/greetings")
public class GreetingController {

    @GetMapping("/greet")
    public ResponseEntity<String> greeting(){
        return ResponseEntity.ok("greating from api hello");
    }
    @PostMapping("/sayGoodBye")
    public ResponseEntity<String> sayGoodBye(){
        return ResponseEntity.ok("goodBye from api sayGoodBye");
    }
}

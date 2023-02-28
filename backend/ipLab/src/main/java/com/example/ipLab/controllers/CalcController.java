package com.example.ipLab.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalcController {

    @GetMapping("/second")
    public int second(@RequestParam(value = "num") int num){
        return num*num;
    }

    @GetMapping("/root")
    public double root(@RequestParam(value = "num") int num){
        return Math.sqrt(num);
    }

    @GetMapping("/fact")
    public int fact(@RequestParam(value = "num") int num){
        int res = 1;
        for (int i = 2; i <= num; i++) {
            res *= i;
        }
        return res;
    }

    @GetMapping("/digit")
    public int digit(@RequestParam(value = "num") int num){
        if (num < 0) num *= -1;
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}

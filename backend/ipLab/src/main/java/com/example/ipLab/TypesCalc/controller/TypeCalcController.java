package com.example.ipLab.TypesCalc.controller;

import com.example.ipLab.TypesCalc.Service.CalcService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TypeCalcController {
    private final CalcService calcService;

    public TypeCalcController(CalcService calcService) {this.calcService = calcService;}

    @GetMapping("/CalcSum")
    public Object calcSum(@RequestParam(value = "obj1", defaultValue = "null objects") Object obj1,
                       @RequestParam(value = "obj2", defaultValue = "provided") Object obj2,
                       @RequestParam(value = "type", defaultValue = "string") String type){
        return calcService.Sum(obj1, obj2, type);
    }

    @GetMapping("/CalcDif")
    public Object calcDif(@RequestParam(value = "obj1", defaultValue = "null objects") Object obj1,
                       @RequestParam(value = "obj2", defaultValue = "provided") Object obj2,
                       @RequestParam(value = "type", defaultValue = "string") String type){
        return calcService.Dif(obj1, obj2, type);
    }

    @GetMapping("/CalcMultiply")
    public Object calcMultiply(@RequestParam(value = "obj1", defaultValue = "null objects") Object obj1,
                       @RequestParam(value = "obj2", defaultValue = "provided") Object obj2,
                       @RequestParam(value = "type", defaultValue = "string") String type){
        return calcService.Multiply(obj1, obj2, type);
    }

    @GetMapping("/CalcDiv")
    public Object calcDiv(@RequestParam(value = "obj1", defaultValue = "null objects") Object obj1,
                       @RequestParam(value = "obj2", defaultValue = "provided") Object obj2,
                       @RequestParam(value = "type", defaultValue = "string") String type){
        return calcService.Div(obj1, obj2, type);
    }
}

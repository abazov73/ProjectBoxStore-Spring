package com.example.ipLab.TypesCalc.Service;

import com.example.ipLab.TypesCalc.domen.TypeCalc;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class CalcService {
    private final ApplicationContext applicationContext;

    public CalcService(ApplicationContext applicationContext){this.applicationContext = applicationContext;}

    public Object Sum(Object obj1, Object obj2, String type){
        final TypeCalc typeCalculator = (TypeCalc) applicationContext.getBean(type);
        if (type.startsWith("int")) return typeCalculator.Sum(Integer.parseInt(obj1.toString()), Integer.parseInt(obj2.toString()));
        return typeCalculator.Sum(obj1, obj2);
    }
    public Object Dif(Object obj1, Object obj2, String type){
        final TypeCalc typeCalculator = (TypeCalc) applicationContext.getBean(type);
        if (type.startsWith("int")) return typeCalculator.Dif(Integer.parseInt(obj1.toString()), Integer.parseInt(obj2.toString()));
        return typeCalculator.Dif(obj1, obj2);
    }
    public Object Multiply(Object obj1, Object obj2, String type){
        final TypeCalc typeCalculator = (TypeCalc) applicationContext.getBean(type);
        if (type.startsWith("int")) return typeCalculator.Multiply(Integer.parseInt(obj1.toString()), Integer.parseInt(obj2.toString()));
        return typeCalculator.Multiply(obj1, obj2);
    }
    public Object Div(Object obj1, Object obj2, String type){
        final TypeCalc typeCalculator = (TypeCalc) applicationContext.getBean(type);
        if (type.startsWith("int")) return typeCalculator.Div(Integer.parseInt(obj1.toString()), Integer.parseInt(obj2.toString()));
        return typeCalculator.Div(obj1, obj2);
    }
}

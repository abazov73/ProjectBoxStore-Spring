package com.example.ipLab.TypesCalc.domen;

import org.springframework.stereotype.Component;

public class TypeCalcInteger implements TypeCalc<Integer>{
    @Override
    public Integer Sum(Integer num1, Integer num2) {
        return num1 + num2;
    }

    @Override
    public Integer Dif(Integer num1, Integer num2) {
        return num1 - num2;
    }

    @Override
    public Integer Multiply(Integer num1, Integer num2) {
        return num1 * num2;
    }

    @Override
    public Integer Div(Integer num1, Integer num2) {
        try {
            return num1 / num2;
        }
        catch (ArithmeticException ex){
            return 0;
        }
    }
}

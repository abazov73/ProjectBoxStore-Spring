package com.example.ipLab.TypesCalc.domen;

public interface TypeCalc<T> {
    T Sum(T obj1, T obj2);
    T Dif(T obj1, T obj2);
    T Multiply(T obj1, T obj2);
    T Div(T obj1, T obj2);
}

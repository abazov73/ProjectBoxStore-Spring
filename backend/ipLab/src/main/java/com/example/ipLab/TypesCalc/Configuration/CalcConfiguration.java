package com.example.ipLab.TypesCalc.Configuration;

import com.example.ipLab.TypesCalc.domen.TypeCalcInteger;
import com.example.ipLab.TypesCalc.domen.TypeCalcString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalcConfiguration {
    private final Logger log = LoggerFactory.getLogger(CalcConfiguration.class);

    @Bean(value="int")
    public TypeCalcInteger createTypeCalcInteger(){
        return new TypeCalcInteger();
    }

    @Bean(value="string")
    public TypeCalcString createTypeCalcString(){
        return new TypeCalcString();
    }
}

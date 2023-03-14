package com.example.ipLab.TypesCalc.domen;

import org.springframework.stereotype.Component;

public class TypeCalcString implements TypeCalc<String> {
    @Override
    public String Sum(String s1, String s2) {
        return s1 + s2;
    }

    @Override
    public String Dif(String s1, String s2) {
        String res = "";
        for (char c : s1.toCharArray()){
            boolean foundInOther = false;
            for (int i = 0; i < s2.length(); i++){
                if (c == s2.charAt(i)) {
                    foundInOther = true;
                    break;
                }
            }
            if (!foundInOther) res += c;
        }
        return res;
    }

    @Override
    public String Multiply(String s1, String s2) {
        String res = "";
        for (char c : s1.toCharArray()){
            boolean foundInOther = false;
            for (int i = 0; i < s2.length(); i++){
                if (c == s2.charAt(i)) {
                    foundInOther = true;
                    break;
                }
            }
            if (foundInOther) res += c;
        }
        return res;
    }

    @Override
    public String Div(String s1, String s2) {
        StringBuilder res = new StringBuilder();
        int maxLength = Integer.max(s1.length(), s2.length());
        for (int i = 0; i < maxLength; i++){
            if (i < s1.length()){
                res.append(s1.charAt(i));
            }
            if (i < s2.length()){
                res.append(s2.charAt(i));
            }
        }
        return res.toString();
    }
}

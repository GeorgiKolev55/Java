package com.company;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public Validator() {
    }

    public  boolean isValidExpression(String expression){
        Pattern pattern = Pattern.compile("(A.*a|B.*b|C.*c)");

        Matcher matcher = pattern.matcher(expression);
        if (matcher.matches()){
           return true;
        }
        return false;
    }
}

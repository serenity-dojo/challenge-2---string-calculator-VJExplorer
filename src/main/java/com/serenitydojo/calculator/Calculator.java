package com.serenitydojo.calculator;

import java.util.*;
import java.util.stream.Collectors;



public class Calculator {

    List<String> items = new ArrayList<>();

    public int evaluate(String expression) {
        int evaluvatedValue=0;

        if (!(expression.isEmpty())){
            items = Arrays.stream(expression.split(" "))
                    .filter(val->!(val.isEmpty()))
                    .collect(Collectors.toList());
            evaluvatedValue = calculateValue(items);
        }

        return evaluvatedValue;

    }


    private int calculateValue(List<String> strItems){

        int itemCount = strItems.size();
        int i=0 ;
        List<Integer> numbers=new ArrayList<>();
        String operator ="";
        while (i<itemCount){
            String val = strItems.get(i);
            if(val.chars().allMatch(Character::isDigit)){
                numbers.add(Integer.valueOf(val));
            }else{
                operator  = val;
            }
            i++;
            if (numbers.size()==2 && !operator.isEmpty()){
                int resultValue =0 ;
                if (operator.equals("+")){
                    resultValue = numbers.get(0) + numbers.get(1);
                }else if (operator.equals("-")){
                    resultValue = numbers.get(0) -numbers.get(1);
                }else if(operator.equals("*")){
                    resultValue = numbers.get(0) *numbers.get(1);
                }else if(operator.equals("/")){
                    resultValue = numbers.get(0) /numbers.get(1);
                }else{
                    throw new IllegalMathsOperatorException("Invalid math operator" +operator);
                }
                numbers.removeAll(numbers);
                numbers.add(resultValue);
                operator ="";
            }

        }
        return numbers.get(0);
    }


}

package com.robson.functions.utils;

import java.util.List;

public class CalculateFunction {

    public static final List<Character> validNumbers = List.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.');

    public static final List<Character> validOperators = List.of('+', '-', '*', '/');

    public static final List<Character> validBackFunctions = List.of('s', 'c', 't', '√');

    public static final List<Character> validFrontFunctions = List.of('²', '³');

    public static final List<Character> validMisc = List.of('D', 'x', '(', ')');

    static FunctionOutPut calculate(float x, List<Character> function) {
        float outputreal = 0;
        float outputimaginary = 0;
        for (int i = 0; i < function.size(); i++) {
            Character c = function.get(i);
            if (validNumbers.contains(c) || c == 'x') {
                ParseInfo info = parseNumber(x, function, i);
                float value = info.amount();
                if (function.size() > info.index()){
                    Character operator = function.get(info.index());
                    if (validOperators.contains(operator)){
                        int next = info.index() + 1;
                        if (function.size() > next){
                            ParseInfo nextInfo = parseNumber(x, function, next);
                            value = gerAritmeticOperation(value, nextInfo.amount(), operator);
                        }
                    }
                    if (validFrontFunctions.contains(operator)){
                        value = getSingleOperation(value, operator).realpart();
                    }
                }
                if (i == 0){
                    outputreal = value;
                }
            }
            if (validBackFunctions.contains(c) && function.size() > i + 1) {
                float number = parseNumber(x, function, i + 1).amount();
                float result = getSingleOperation(number, c).realpart();
                outputimaginary = getSingleOperation(number, c).imaginarypart();
                if (i == 0) {
                    outputreal = result;
                }
            }
        }
        return new FunctionOutPut(outputreal, outputimaginary);
    }

    public static FunctionOutPut getSingleOperation(float number, char operation){
        float realoutput = 0;
        float imaginaryoutput = 0;
        if (operation == '√' && number < 0){
            number = -number;
            imaginaryoutput = (float) Math.sqrt(number);
        }
        else realoutput = switch (operation){
            case '²' -> number * number;
            case '³'-> number * number * number;
            case '√'-> (float) Math.sqrt(number);
            case 's'-> (float) Math.sin(number);
            case 'c'-> (float) Math.cos(number);
            case 't'-> (float) Math.tan(number);
            default -> number;
        };
        return new FunctionOutPut(realoutput, imaginaryoutput);
    }

    public static float gerAritmeticOperation(float a, float b, char operation){
        return switch (operation){
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> a / b;
            default -> 0;
        };
    }

    public static ParseInfo parseNumber(float x, List<Character> function, int start){
        StringBuilder sb = new StringBuilder();
        float amount = 0;
        int index = start;
        if (function.get(start) == 'x') {
            amount = x;
        }
        for (int i = start; i < function.size(); i++) {
            if ((function.get(i) == '.' && function.size() > i + 1 && validNumbers.contains(function.get(i + 1)))|| (validNumbers.contains(function.get(i)) && function.get(i) != '.')){
                sb.append(function.get(i));
                index = i;
            }
            else break;
        }
        if (!sb.isEmpty()){
            amount = (float) Double.parseDouble(sb.toString());
        }
        return new ParseInfo(amount, index + 1);
    }
}

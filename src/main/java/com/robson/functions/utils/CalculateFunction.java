package com.robson.functions.utils;

import com.robson.functions.utils.number.AbstractNumber;
import com.robson.functions.utils.number.ComplexNumber;
import com.robson.functions.utils.number.RealNumber;

import java.util.List;

public class CalculateFunction {

    public static final List<Character> validNumbers = List.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.');

    public static final List<Character> validOperators = List.of('+', '-', '*', '/', '^');

    public static final List<Character> validBackFunctions = List.of('s', 'c', 't', '√')
            ;
    public static final List<Character> validMisc = List.of('D', 'x', '(', ')', 'e', 'i');

    static FunctionOutPut calculate(float x, List<Character> function) {
        FunctionOutPut output = new FunctionOutPut(new RealNumber(0), new ComplexNumber(0));
        int i = 0;
        while (i < function.size()) {
            char c = function.get(i);

            if (validBackFunctions.contains(c) && i + 1 < function.size()) {
                ParseInfo arg = parseNumber(x, function, i + 1);
                output = getSingleOperation(arg.number(), c);
                i = arg.index();
            }
            else if (validNumbers.contains(c) || c == 'x' || c == 'e' || c == 'i') {
                ParseInfo left = parseNumber(x, function, i);
                AbstractNumber number = left.number();
                i = left.index();
                if (c == 'e'){
                    return new FunctionOutPut(new RealNumber((float) Math.cos(x)), new ComplexNumber((float) Math.sin(x)));
                }
                if (i < function.size()) {
                    char op = function.get(i);
                    if (validOperators.contains(op) && i + 1 < function.size()) {
                        ParseInfo right = parseNumber(x, function, i + 1);
                        output = gerAritmeticOperation(number, right.number(), op);
                        i = right.index();
                    }
                    else {
                        output = new FunctionOutPut(number);
                    }
                }
                else {
                    output = new FunctionOutPut(number);
                }
            }
            else {
                i++;
            }
        }
        return output;
    }

    public static FunctionOutPut getSingleOperation(AbstractNumber number, char operation){
        if (operation == '√'){
            return number.sqrt();
        }
          switch (operation){
            case 's'-> number.setValue(number.sin());
            case 'c'->  number.setValue(number.cos());
            case 't'->  number.setValue(number.tan());
        };
        return number instanceof RealNumber ? new FunctionOutPut((RealNumber) number, new ComplexNumber(0)) : new FunctionOutPut(new RealNumber(0), (ComplexNumber) number);
    }

    public static FunctionOutPut gerAritmeticOperation(AbstractNumber a, AbstractNumber b, char operation){
        return switch (operation){
            case '+' -> a.add(b);
            case '-' -> a.sub(b);
            case '*' -> a.mul(b);
            case '/' -> a.div(b);
            case '^' -> a.pow(b);
            default -> a instanceof RealNumber ? new FunctionOutPut((RealNumber) a, new ComplexNumber(0)) : new FunctionOutPut(new RealNumber(0), (ComplexNumber) a);
        };
    }

    public static ParseInfo parseNumber(float x, List<Character> function, int start){
        StringBuilder sb = new StringBuilder();
        float amount = 0;
        int index = start;
        boolean iscomplex = false;
        if (function.get(start) == 'x') {
            amount = x;
        }
        if (function.get(start) == 'e'){
            amount = Constants.EULER;
        }
        if (function.get(start) == 'i'){
            iscomplex = true;
        }
        for (int i = start; i < function.size(); i++) {
            if (function.get(i) == 'x') {
                amount = x;
                break;
            }
            if (function.get(i) == 'e'){
                amount = Constants.EULER;
                break;
            }
            if ((function.get(i) == '.' && function.size() > i + 1 && validNumbers.contains(function.get(i + 1)))|| (validNumbers.contains(function.get(i)) && function.get(i) != '.')){
                sb.append(function.get(i));
                index = i;
            }
            else break;
        }
        if (!sb.isEmpty()){
            amount = (float) Double.parseDouble(sb.toString());
        }
        AbstractNumber number = iscomplex ? new ComplexNumber(amount) : new RealNumber(amount);
        return new ParseInfo(number, index + 1);
    }
}

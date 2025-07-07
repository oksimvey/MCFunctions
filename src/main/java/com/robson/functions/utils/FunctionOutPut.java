package com.robson.functions.utils;

import com.robson.functions.utils.number.AbstractNumber;
import com.robson.functions.utils.number.ComplexNumber;
import com.robson.functions.utils.number.RealNumber;

public class FunctionOutPut {

    private final RealNumber realNumber;

    private final ComplexNumber complexNumber;

    public FunctionOutPut(RealNumber realNumber, ComplexNumber complexNumber) {
        this.realNumber = realNumber;
        this.complexNumber = complexNumber;
    }

    public FunctionOutPut(AbstractNumber number) {
        if (number instanceof RealNumber) {
            this.realNumber = (RealNumber) number;
            this.complexNumber = new ComplexNumber(0);
            return;
        }
        this.realNumber = new RealNumber(0);
        this.complexNumber = (ComplexNumber) number;
    }

    public RealNumber realNumber() {
        return realNumber;
    }

    public ComplexNumber complexNumber() {
        return complexNumber;
    }

    public float realpart(){
        return realNumber.value();
    }

    public float imaginarypart(){
        return complexNumber.value();
    }

    public void setRealNumber(RealNumber realNumber) {
        this.realNumber.setValue(realNumber.value());
    }

    public void setComplexNumber(ComplexNumber complexNumber) {
        this.complexNumber.setValue(complexNumber.value());
    }

}

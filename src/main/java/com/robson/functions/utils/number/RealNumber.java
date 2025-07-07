package com.robson.functions.utils.number;

import com.robson.functions.utils.Constants;
import com.robson.functions.utils.FunctionOutPut;

public class RealNumber extends AbstractNumber{
    
    public RealNumber(float number) {
        super(number);
    }

    @Override
    public FunctionOutPut mul(AbstractNumber number) {
        if (number instanceof RealNumber number1) {
            return new FunctionOutPut(new RealNumber(this.value() * number1.value()), new ComplexNumber(0));
        }
        else if (number instanceof ComplexNumber number1){
            return new FunctionOutPut(new RealNumber(0), new ComplexNumber(this.value() * number1.value()));
        }
        return new FunctionOutPut(this, new ComplexNumber(0));
    }

    @Override
    public FunctionOutPut div(AbstractNumber number) {
        if (number.value() == 0) {
            number = new RealNumber(1);
        }
        if (number instanceof RealNumber number1) {
            return new FunctionOutPut(new RealNumber(this.value() / number1.value()), new ComplexNumber(0));
        }
        else if (number instanceof ComplexNumber number1){
            return new FunctionOutPut(new RealNumber(0), new ComplexNumber(this.value() / number1.value()));
        }
        return new FunctionOutPut(this, new ComplexNumber(0));
    }

    @Override
    public FunctionOutPut add(AbstractNumber number) {
        if (number instanceof RealNumber number1) {
            return new FunctionOutPut(new RealNumber(this.value() + number1.value()), new ComplexNumber(0));
        }
        else if (number instanceof ComplexNumber number1){
            return new FunctionOutPut(this, number1);
        }
        return new FunctionOutPut(this, new ComplexNumber(0));
    }

    @Override
    public FunctionOutPut sub(AbstractNumber number) {
        if (number instanceof RealNumber number1) {
            return new FunctionOutPut(new RealNumber(this.value() - number1.value()), new ComplexNumber(0));
        }
        else if (number instanceof ComplexNumber number1){
            return new FunctionOutPut(this, new ComplexNumber(-number1.value()));
        }
        return new FunctionOutPut(this, new ComplexNumber(0));
    }

    @Override
    public FunctionOutPut pow(AbstractNumber number) {
        RealNumber realNumber = new RealNumber(0);
        ComplexNumber complexNumber = new ComplexNumber(0);
        if (number instanceof RealNumber) {
            realNumber.setValue((float) Math.pow(this.value(), number.value()));
        }
        else if (number instanceof ComplexNumber) {
            if (this.value() == Constants.EULER) {
                realNumber.setValue(number.cos());
                complexNumber.setValue(number.sin());
            }
            else {
                float value = (float) Math.log(this.value());
                realNumber.setValue((float) Math.cos(number.value() * value));
                complexNumber.setValue((float) Math.sin(number.value() * value));
            }
        }
        return new FunctionOutPut(realNumber, complexNumber);
    }

    @Override
    public FunctionOutPut sqrt() {
        if (this.value() < 0) {
            this.setValue(-this.value());
            return new FunctionOutPut(new RealNumber(0), new ComplexNumber((float) Math.sqrt(this.value())));
        }
        return new FunctionOutPut(new RealNumber((float) Math.sqrt(this.value())), new ComplexNumber(0));
    }
}

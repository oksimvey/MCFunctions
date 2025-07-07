package com.robson.functions.utils.number;

import com.robson.functions.utils.FunctionOutPut;

public abstract class AbstractNumber {

    private float value;

    protected AbstractNumber(float value) {
        this.value = value;
    }

    public final float value() {
        return this.value;
    }

    public final void setValue(float value) {
        this.value = value;
    }

    public abstract FunctionOutPut mul(AbstractNumber number);

    public abstract FunctionOutPut div(AbstractNumber number);

    public abstract FunctionOutPut add(AbstractNumber number);

    public abstract FunctionOutPut sub(AbstractNumber number);

    public abstract FunctionOutPut pow(AbstractNumber number);

    public abstract FunctionOutPut sqrt();

    public final float sin(){
        return (float) Math.sin(this.value());
    }

    public final float cos(){
        return (float) Math.cos(this.value());
    }

    public final float tan(){
        return (float) Math.tan(this.value());
    }

}

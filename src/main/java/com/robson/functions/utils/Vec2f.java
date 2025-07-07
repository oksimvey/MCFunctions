package com.robson.functions.utils;

import static com.robson.functions.utils.MathUtils.rotate2DVector;

public record Vec2f(float x, float y){

    public Vec2f rotate(float degrees) {
        return rotate2DVector(this, degrees);
    }

    public Vec2f add(Vec2f vec) {
        return new Vec2f(x + vec.x(), y + vec.y());
    }

    public Vec2f add(float x, float y) {
        return new Vec2f(this.x + x, this.y + y);
    }
}

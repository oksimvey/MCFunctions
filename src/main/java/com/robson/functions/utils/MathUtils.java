package com.robson.functions.utils;

public interface MathUtils {

    static float degreeToRadians(float degrees) {
        return (float) (degrees * Math.PI / 180f);
    }
    static Vec2f rotate2DVector(Vec2f vec, float degrees) {
        float theta = degreeToRadians(degrees);
        float x = (float) ((vec.x() * Math.cos(theta)) - (vec.y() * Math.sin(theta)));
        float z = (float) ((vec.x() * Math.sin(theta)) + (vec.y() * Math.cos(theta)));
        return new Vec2f(x, z);
    }
}

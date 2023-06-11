package com.rumaruka.riskofmine.utils;

public class ROMMathUtils {

    /*
     Math Utils class
     */
    public static double summ(double x, double y) {
        return x + y;

    }

    public static double multiply(double x, double y) {
        return x * y;
    }

    public static float divide(float x, float y) {
        if (y == 0) {
            throw new ArithmeticException("Divide Null");
        }
        return x / y;
    }

    public static float percent(float x) {
        return x / 100;
    }


}

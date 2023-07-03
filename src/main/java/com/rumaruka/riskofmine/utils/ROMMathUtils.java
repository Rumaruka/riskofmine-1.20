package com.rumaruka.riskofmine.utils;

public class ROMMathUtils {
    /**
    This code contains several math methods.
    The code starts by defining a class named "ROMMathUtils".
    The code then has a "summ" method that returns the sum of 2 numbers.
    The code then has a "multiply" method that returns the product of 2 numbers.
    The code then has a "divide" method that returns the quotient of 2 numbers.
    If the divisor is 0, an error will be thrown.
    The code then has a "percent" method that returns the percentage of a number.
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
        return x * 0.01f;
    }

}
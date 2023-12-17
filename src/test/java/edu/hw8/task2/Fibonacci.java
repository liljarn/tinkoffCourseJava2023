package edu.hw8.task2;

public class Fibonacci {
    public static int getFib(int n) {
        if (n == 1 || n == 0) {
            return n;
        }
        return getFib(n - 1) + getFib(n - 2);
    }
}

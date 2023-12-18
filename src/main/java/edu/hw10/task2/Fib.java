package edu.hw10.task2;

public class Fib implements FibCalculator {
    @Override
    public long fib(int num) {
        if (num < 2) {
            return num;
        }
        return fib(num - 2) + fib(num - 1);
    }
}

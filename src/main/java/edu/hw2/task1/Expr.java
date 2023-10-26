package edu.hw2.task1;

public sealed interface Expr {
    double evaluate();

    record Constant(int constant) implements Expr {
        @Override
        public double evaluate() {
            return constant;
        }
    }

    record Negate(double constant) implements Expr {
        public Negate(Expr constant) {
            this(constant.evaluate());
        }

        @Override
        public double evaluate() {
            return -constant;
        }
    }

    record Exponent(double constant, double power) implements Expr {
        public Exponent(Expr constant, Expr power) {
            this(constant.evaluate(), power.evaluate());
        }

        public Exponent(Expr constant, double power) {
            this(constant.evaluate(), power);
        }

        public Exponent(double constant, Expr power) {
            this(constant, power.evaluate());
        }

        @Override
        public double evaluate() {
            return Math.pow(constant, power);
        }
    }

    record Addition(double firstNumber, double secondNumber) implements Expr {
        public Addition(Expr firstNumber, Expr secondNumber) {
            this(firstNumber.evaluate(), secondNumber.evaluate());
        }

        public Addition(double firstNumber, Expr secondNumber) {
            this(firstNumber, secondNumber.evaluate());
        }

        public Addition(Expr firstNumber, double secondNumber) {
            this(firstNumber.evaluate(), secondNumber);
        }

        @Override
        public double evaluate() {
            return firstNumber + secondNumber;
        }
    }

    record Multiplication(double firstNumber, double secondNumber) implements Expr {
        public Multiplication(Expr firstNumber, Expr secondNumber) {
            this(firstNumber.evaluate(), secondNumber.evaluate());
        }

        public Multiplication(Expr firstNumber, double secondNumber) {
            this(firstNumber.evaluate(), secondNumber);
        }

        public Multiplication(double firstNumber, Expr secondNumber) {
            this(firstNumber, secondNumber.evaluate());
        }

        @Override
        public double evaluate() {
            return firstNumber * secondNumber;
        }
    }
}

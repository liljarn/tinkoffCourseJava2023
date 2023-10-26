package edu.hw2.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import edu.hw2.task1.Expr.*;

public class CalculatorTest {
    @Test
    @DisplayName("Тест калькулятора")
    void calculatorTest() {
        var two = new Constant(2);
        var four = new Constant(4);
        var negOne = new Negate(new Expr.Constant(1));
        var sumTwoFour = new Addition(two, four);
        var mult = new Multiplication(sumTwoFour, negOne);
        var exp = new Exponent(mult, 2);
        var res = new Addition(exp, new Constant(1));

        assertThat(res.evaluate()).isEqualTo(37);
    }
}

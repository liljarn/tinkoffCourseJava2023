package edu.hw11.task2;

import lombok.SneakyThrows;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.matcher.ElementMatchers.isDeclaredBy;
import static net.bytebuddy.matcher.ElementMatchers.returns;
import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

public class ChangeMethodByteBuddyTest {
    @SneakyThrows
    @Test
    @DisplayName("ByteBuddy method delegation test")
    public void sum_shouldReturnMultiplication_notSum() {
        var type = new ByteBuddy()
            .subclass(ArithmeticUtils.class)
            .method(ElementMatchers.named("sum"))
            .intercept(MethodDelegation.to(ArithmeticUtilsMult.class))
            .make()
            .load(getClass().getClassLoader())
            .getLoaded()
            .getDeclaredConstructor()
            .newInstance().sum(2, 5);
        assertThat(type).isEqualTo(10);
    }

    public static class ArithmeticUtilsMult {
        public static int sum(int a, int b) {
            return a * b;
        }
    }
}

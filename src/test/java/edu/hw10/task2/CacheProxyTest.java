package edu.hw10.task2;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CacheProxyTest {
    @Test
    public void aboba() {
        FibCalculator fibCalculator = new Fib();
        FibCalculator proxy = CacheProxy.create(fibCalculator, FibCalculator.class);
//        System.out.println(proxy.fib(10));
//        System.out.println(proxy.fib(10));
        assertThat(proxy.fib(10)).isEqualTo(proxy.fib(10));
    }


}

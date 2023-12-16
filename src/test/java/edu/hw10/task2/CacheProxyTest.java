package edu.hw10.task2;

import edu.hw10.task2.proxy.CacheProxy;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CacheProxyTest {
    @Test
    public void aboba() {
        FibCalculator fibCalculator = new Fib();
        FibCalculator proxy = CacheProxy.create(fibCalculator, FibCalculator.class);
        assertThat(proxy.fib(10)).isEqualTo(proxy.fib(10));
    }


}

package edu.project5;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import lombok.SneakyThrows;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

@State(Scope.Thread)
public class FastReflection {
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
            .include(FastReflection.class.getSimpleName())
            .shouldFailOnError(true)
            .shouldDoGC(true)
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.NANOSECONDS)
            .forks(1)
            .warmupForks(1)
            .warmupIterations(1)
            .warmupTime(TimeValue.seconds(5))
            .measurementIterations(1)
            .measurementTime(TimeValue.seconds(5))
            .build();

        new Runner(options).run();
    }

    private record Student(String name, String surname) {
    }

    private Method method;
    private MethodHandle methodHandle;
    private Student student;
    private Function<Student, String> lambda;

    @SneakyThrows
    @Setup
    public void setup() {
        student = new Student("Maksim", "Ustinov");
        method = Student.class.getDeclaredMethod("name");
        methodHandle = MethodHandles.lookup().findGetter(Student.class, "name", String.class);

        CallSite site = LambdaMetafactory.metafactory(
            MethodHandles.lookup(),
            "apply",
            MethodType.methodType(Function.class),
            MethodType.methodType(Object.class, Object.class),
            MethodHandles.lookup().findVirtual(Student.class, "name", MethodType.methodType(String.class)),
            MethodType.methodType(String.class, Student.class)
        );
        lambda = (Function<Student, String>) site.getTarget().invokeExact();
    }

    @Benchmark
    public void directAccess(Blackhole bh) {
        String name = student.name();
        bh.consume(name);
    }

    @SneakyThrows
    @Benchmark
    public void reflection(Blackhole bh) {
        String name = (String) method.invoke(student);
        bh.consume(name);
    }

    @Benchmark
    @SneakyThrows
    public void handleAccess(Blackhole bh) {
        String name = (String) methodHandle.invoke(student);
        bh.consume(name);
    }

    @Benchmark
    @SneakyThrows
    public void lambdaHandleAccess(Blackhole bh) {
        String name = lambda.apply(student);
        bh.consume(name);
    }
}

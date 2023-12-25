package edu.hw7.task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.concurrent.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class CachingServiceTest {
    @Test
    @DisplayName("ReadWriteLockCachingService delete method test")
    public void readWriteLockPersonDatabase_shouldDeleteCorrectlyWithMultithreads() {
        PersonDatabase personDatabase = new ReadWriteLockCachingService();
        deleteTest(personDatabase);
    }

    @Test
    @DisplayName("ReadWriteLockCachingService find test")
    public void readWriteLockPersonDatabase_shouldCorrectlyReturnPersonWithMultithreads() {
        PersonDatabase personDatabase = new ReadWriteLockCachingService();
        findTest(personDatabase);
    }

    @Test
    @DisplayName("SynchronizedCachingService delete method test")
    public void synchronizedPersonDatabase_shouldDeleteCorrectlyWithMultithreads() {
        PersonDatabase personDatabase = new SynchronizedCachingService();
        deleteTest(personDatabase);
    }

    @Test
    @DisplayName("SynchronizedCachingService find test")
    public void synchronizedPersonDatabase_shouldCorrectlyReturnPersonWithMultithreads() {
        PersonDatabase personDatabase = new SynchronizedCachingService();
        findTest(personDatabase);
    }

    private void deleteTest(PersonDatabase personDatabase) {
        Person person = new Person(123, "Ivan", "Tver", "+79996665212");
        Person secondPerson = new Person(1234, "Ivan", "Moscow", "+79996662521");
        try (ExecutorService executorService = Executors.newFixedThreadPool(4)) {
            CountDownLatch countDownLatch = new CountDownLatch(4);
            executorService.execute(() -> {
                personDatabase.add(person);
                countDownLatch.countDown();
            });
            executorService.execute(() -> {
                personDatabase.add(secondPerson);
                countDownLatch.countDown();
            });
            executorService.execute(() -> {
                personDatabase.delete(123);
                countDownLatch.countDown();
            });
            Future<List<Person>> names = executorService.submit(() -> {
                List<Person> persons = personDatabase.findByName("Ivan");
                countDownLatch.countDown();
                return persons;
            });
            countDownLatch.await();
            assertThat(names.get()).doesNotContain(person);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private void findTest(PersonDatabase personDatabase) {
        Person person = new Person(123, "Ivan", "Tver", "+79996665212");
        try (ExecutorService executorService = Executors.newFixedThreadPool(4)) {
            executorService.execute(() -> personDatabase.add(person));
            Future<List<Person>> names =
                executorService.submit(() -> personDatabase.findByName(person.name()));
            Future<List<Person>> addresses =
                executorService.submit(() -> personDatabase.findByAddress(person.address()));
            Future<List<Person>> phones =
                executorService.submit(() -> personDatabase.findByPhone(person.phoneNumber()));
            executorService.shutdown();
            executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
            assertAll(
                () -> assertThat(names.get()).contains(person),
                () -> assertThat(addresses.get()).contains(person),
                () -> assertThat(phones.get()).contains(person)
            );
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

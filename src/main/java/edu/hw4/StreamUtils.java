package edu.hw4;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class StreamUtils {
    private static final int MAX_HEIGHT = 100;

    private StreamUtils() {
    }

    public static List<Animal> streamSortByHeight(List<Animal> animals) {
        return animals.stream().sorted(Comparator.comparing(Animal::height)).toList();
    }

    public static List<Animal> streamReversedSortByWeight(List<Animal> animals, int firstElements) {
        return animals.stream().sorted(Comparator.comparing(Animal::weight)
            .reversed()).limit(firstElements).toList();
    }

    public static Map<Animal.Type, Integer> streamTypesMap(List<Animal> animals) {
        return animals.stream().collect(Collectors
            .toMap(Animal::type, s -> 1, Integer::sum));
    }

    public static Animal streamMostLongNameAnimal(List<Animal> animals) {
        return animals.stream().max(Comparator.comparing(animal -> animal.name().length()))
            .orElse(null);
    }

    public static Animal.Sex streamMostCommonAnimalSex(List<Animal> animals) {
        return animals.stream().collect(Collectors
                .groupingBy(Animal::sex, Collectors.counting()))
            .entrySet().stream()
            .max(Map.Entry.comparingByValue()).get().getKey();
    }

    public static Map<Animal.Type, Animal> streamMostHeavyAnimalMap(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.toMap(
                Animal::type,
                Function.identity(),
                BinaryOperator.maxBy(Comparator.comparing(Animal::weight))
            ));
    }

    public static Animal streamMostOldAnimal(List<Animal> animals, int firstOldest) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::age).reversed())
            .toList().get(firstOldest - 1);
    }

    public static Optional<Animal> streamMostHeavyBelowHeight(List<Animal> animals, int maxHeight) {
        return animals.stream()
            .filter(animal -> animal.height() < maxHeight)
            .max(Comparator.comparing(Animal::weight));
    }

    public static Integer streamPawsCount(List<Animal> animals) {
        return animals.stream().mapToInt(Animal::paws).sum();
    }

    public static List<Animal> streamListOfAnimalsWithAgeNotEqualPaws(List<Animal> animals) {
        return animals.stream().filter(animal -> animal.age() != animal.paws()).toList();
    }

    public static List<Animal> streamAnimalsCanBiteAndHigherThenOneHundred(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.bites() && animal.height() > MAX_HEIGHT).toList();
    }

    public static Integer streamNumberOfAnimalsHeavierThanHeight(List<Animal> animals) {
        return (int) (animals.stream()
            .filter(animal -> animal.weight() > animal.height()).count());
    }

    public static List<Animal> streamAnimalNameMoreThanTwoWords(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.name().split(" ").length > 2).toList();
    }

    public static Boolean streamIsDogInAnimalListHigherThan(List<Animal> animals, int minHeight) {
        return animals.stream()
            .anyMatch(animal -> animal.height() > minHeight && animal.type()
                == Animal.Type.DOG);
    }

    public static Integer streamSumOfAnimalWeight(List<Animal> animals, int minAge, int maxAge) {
        return animals.stream().filter(animal -> animal.age() > minAge && animal.age() < maxAge)
            .mapToInt(Animal::weight).sum();
    }

    public static List<Animal> streamSortAnimalsByTypeSexName(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::type).thenComparing(Animal::sex)
                .thenComparing(Animal::name)).toList();
    }

    public static Boolean streamIsSpidersBitesMoreThanDogs(List<Animal> animals) {
        var map = animals.stream()
            .collect(Collectors
                .groupingBy(Animal::type, Collectors.averagingInt(animal -> animal.bites() ? 1 : 0)));
        return map
            .getOrDefault(Animal.Type.SPIDER, -1.0) > map
            .getOrDefault(Animal.Type.DOG, 2.0);
    }

    @SafeVarargs public static Animal streamMostHeavyFishInLists(List<Animal>... animals) {
        return Arrays.stream(animals).flatMap(Collection::stream)
            .filter(animal -> animal.type() == Animal.Type.FISH)
            .max(Comparator.comparing(Animal::weight)).orElse(null);
    }

    public static Map<String, Set<ValidationError>> streamOfValidationExceptions(List<Animal> animals) {
        return animals.stream().filter(animal -> !animal.validation().isEmpty())
            .collect(Collectors.toMap(Animal::name, Animal::validation));
    }

    public static Map<String, String> streamOfValidationExceptionsPrettier(List<Animal> animals) {
        return animals.stream().filter(animal -> !animal.validation().isEmpty())
            .collect(Collectors.toMap(
                Animal::name,
                animal -> animal.validation().stream().map(ValidationError::field).sorted()
                    .collect(Collectors.joining(", "))
            ));
    }
}

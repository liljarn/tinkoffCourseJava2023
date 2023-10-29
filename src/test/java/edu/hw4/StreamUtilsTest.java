package edu.hw4;

import com.google.common.collect.Comparators;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StreamUtilsTest {
    private List<Animal> animals;

    @BeforeEach public void makeList() {
        animals = new ArrayList<>();
        animals.add(new Animal("Borya", Animal.Type.CAT, Animal.Sex.M,
            5, 36, 6, true
        ));
        animals.add(new Animal("Gena", Animal.Type.CAT, Animal.Sex.M,
            4, 50, 13, true
        ));
        animals.add(new Animal("Grisha", Animal.Type.DOG, Animal.Sex.M,
            10, 103, 20, true
        ));
        animals.add(new Animal("Alisa", Animal.Type.DOG, Animal.Sex.F,
            11, 120, 36, false
        ));
        animals.add(new Animal("Katya", Animal.Type.BIRD, Animal.Sex.F,
            2, 10, 2, false
        ));
        animals.add(new Animal("Garik", Animal.Type.BIRD, Animal.Sex.M,
            3, 11, 3, false
        ));
        animals.add(new Animal("Egor", Animal.Type.FISH, Animal.Sex.M,
            1, 5, 1, false
        ));
        animals.add(new Animal("Sema", Animal.Type.FISH, Animal.Sex.M,
            3, 17, 10, false
        ));
        animals.add(new Animal("Jenya", Animal.Type.SPIDER, Animal.Sex.F,
            1, 3, 1, true
        ));
        animals.add(new Animal("Liza", Animal.Type.SPIDER, Animal.Sex.M,
            1, 4, 2, true
        ));
    }

    @Test
    @DisplayName("Sort by height test")
    public void streamSortByHeight_shouldReturnSortedByHeightList() {
        assertTrue(Comparators.isInOrder(
            StreamUtils.streamSortByHeight(animals),
            Comparator.comparing(Animal::height)
        ));
    }

    public static Stream<Arguments> weightSort() {
        return Stream.of(
            Arguments.of(
                3,
                List.of(
                    new Animal("Alisa", Animal.Type.DOG, Animal.Sex.F,
                        11, 120, 36, false
                    ),
                    new Animal("Grisha", Animal.Type.DOG, Animal.Sex.M,
                        10, 103, 20, true
                    ),
                    new Animal("Gena", Animal.Type.CAT, Animal.Sex.M,
                        4, 50, 13, true
                    )
                )
            ),
            Arguments.of(
                1,
                List.of(
                    new Animal("Alisa", Animal.Type.DOG, Animal.Sex.F,
                        11, 120, 36, false
                    )
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("weightSort")
    @DisplayName("Sort by weight test")
    public void streamSortByWeight_shouldReturnSortedByWeightFirstElements(
        int firstElements,
        List<Animal> expected
    ) {
        assertThat(StreamUtils.streamReversedSortByWeight(animals, firstElements))
            .isEqualTo(expected);
    }

    @Test
    @DisplayName("Map of types test")
    public void streamTypesMap_shouldReturnMapOfTypesAndIntegers() {
        assertThat(StreamUtils.streamTypesMap(animals)).isEqualTo(Map.of(
            Animal.Type.CAT, 2,
            Animal.Type.DOG, 2,
            Animal.Type.FISH, 2,
            Animal.Type.BIRD, 2,
            Animal.Type.SPIDER, 2
        ));
    }

    @Test
    @DisplayName("Longest name animal test")
    public void streamMostLongNameAnimal_shouldReturnAnimalWithLongestName() {
        assertThat(StreamUtils.streamMostLongNameAnimal(animals)).isEqualTo(
            new Animal("Grisha", Animal.Type.DOG, Animal.Sex.M,
                10, 103, 20, true
            )
        );
    }

    @Test
    @DisplayName("Most common sex test")
    public void streamMostCommonAnimalSex_shouldReturnMostCommonSex() {
        assertThat(StreamUtils.streamMostCommonAnimalSex(animals)).isEqualTo(Animal.Sex.M);
    }

    @Test
    @DisplayName("Most heavy animals test")
    public void streamMostHeavyAnimalMap_shouldReturnMapWithTypeAndAnimal() {
        assertThat(StreamUtils.streamMostHeavyAnimalMap(animals)).isEqualTo(Map.of(
            Animal.Type.CAT, new Animal("Gena", Animal.Type.CAT, Animal.Sex.M,
                4, 50, 13, true
            ),
            Animal.Type.DOG, new Animal("Alisa", Animal.Type.DOG, Animal.Sex.F,
                11, 120, 36, false
            ),
            Animal.Type.FISH, new Animal("Sema", Animal.Type.FISH, Animal.Sex.M,
                3, 17, 10, false
            ),
            Animal.Type.BIRD, new Animal("Garik", Animal.Type.BIRD, Animal.Sex.M,
                3, 11, 3, false
            ),
            Animal.Type.SPIDER, new Animal("Liza", Animal.Type.SPIDER, Animal.Sex.M,
                1, 4, 2, true
            )
        ));
    }

    public static Stream<Arguments> mostOldest() {
        return Stream.of(
            Arguments.of(
                1,
                new Animal(
                    "Alisa", Animal.Type.DOG, Animal.Sex.F,
                    11, 120, 36, false
                )
            ),
            Arguments.of(
                2,
                new Animal("Grisha", Animal.Type.DOG, Animal.Sex.M,
                    10, 103, 20, true
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("mostOldest")
    @DisplayName("Oldest animal test")
    public void streamMostOldAnimal_shouldReturnMostOldAnimal(int mostOldest, Animal expected) {
        assertThat(StreamUtils.streamMostOldAnimal(animals, mostOldest)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Most heavy below height test")
    public void streamMostHeavyBelowHeight_shouldReturnOptionalAnimal() {
        assertAll(
            () -> assertFalse(StreamUtils.streamMostHeavyBelowHeight(animals, 2)
                .isPresent()),
            () -> assertTrue(StreamUtils.streamMostHeavyBelowHeight(animals, 10)
                .isPresent()),
            () -> assertThat(StreamUtils.streamMostHeavyBelowHeight(animals, 10)
                .orElse(null))
                .isEqualTo(new Animal(
                    "Liza", Animal.Type.SPIDER, Animal.Sex.M,
                    1, 4, 2, true
                ))
        );
    }

    @Test
    @DisplayName("Paws amount test")
    public void streamPawsCount_shouldReturnAmountOfPaws() {
        assertThat(StreamUtils.streamPawsCount(animals)).isEqualTo(36);
    }

    @Test
    @DisplayName("Age doesn't equal paws list test")
    public void streamListOfAnimalsWithAgeNotEqualPaws_shouldReturnList() {
        assertThat(StreamUtils.streamListOfAnimalsWithAgeNotEqualPaws(animals)).isEqualTo(
            List.of(
                new Animal("Borya", Animal.Type.CAT, Animal.Sex.M,
                    5, 36, 6, true
                ),
                new Animal("Grisha", Animal.Type.DOG, Animal.Sex.M,
                    10, 103, 20, true
                ),
                new Animal("Alisa", Animal.Type.DOG, Animal.Sex.F,
                    11, 120, 36, false
                ),
                new Animal("Garik", Animal.Type.BIRD, Animal.Sex.M,
                    3, 11, 3, false
                ),
                new Animal("Egor", Animal.Type.FISH, Animal.Sex.M,
                    1, 5, 1, false
                ),
                new Animal("Sema", Animal.Type.FISH, Animal.Sex.M,
                    3, 17, 10, false
                ),
                new Animal("Jenya", Animal.Type.SPIDER, Animal.Sex.F,
                    1, 3, 1, true
                ),
                new Animal("Liza", Animal.Type.SPIDER, Animal.Sex.M,
                    1, 4, 2, true
                )
            )
        );
    }

    @Test
    @DisplayName("Can bite and higher than 100 list test")
    public void streamAnimalsCanBiteAndHigherThenOneHundred_shouldReturnList() {
        assertThat(StreamUtils.streamAnimalsCanBiteAndHigherThenOneHundred(animals)).isEqualTo(
            List.of(
                new Animal("Grisha", Animal.Type.DOG, Animal.Sex.M,
                    10, 103, 20, true
                )
            )
        );
    }

    public static Stream<Arguments> heavierThanHeightTest() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Animal("Albert", Animal.Type.FISH, Animal.Sex.M,
                        3, 20, 21, false
                    ),
                    new Animal("Franchesco", Animal.Type.FISH, Animal.Sex.M,
                        3, 20, 15, false
                    )
                ), 1
            ),
            Arguments.of(
                List.of(
                    new Animal("Borya", Animal.Type.CAT, Animal.Sex.M,
                        5, 36, 6, true
                    ),
                    new Animal("Gena", Animal.Type.CAT, Animal.Sex.M,
                        4, 50, 13, true
                    ),
                    new Animal("Grisha", Animal.Type.DOG, Animal.Sex.M,
                        10, 103, 20, true
                    ),
                    new Animal("Alisa", Animal.Type.DOG, Animal.Sex.F,
                        11, 120, 36, false
                    ),
                    new Animal("Katya", Animal.Type.BIRD, Animal.Sex.F,
                        2, 10, 2, false
                    ), new Animal("Garik", Animal.Type.BIRD, Animal.Sex.M,
                        3, 11, 3, false
                    ),
                    new Animal("Egor", Animal.Type.FISH, Animal.Sex.M,
                        1, 5, 1, false
                    ),
                    new Animal("Sema", Animal.Type.FISH, Animal.Sex.M,
                        3, 17, 10, false
                    ), new Animal("Jenya", Animal.Type.SPIDER, Animal.Sex.F,
                        1, 3, 1, true
                    ),
                    new Animal("Liza", Animal.Type.SPIDER, Animal.Sex.M,
                        1, 4, 2, true
                    )
                ), 0
            )
        );
    }

    @ParameterizedTest
    @MethodSource("heavierThanHeightTest")
    @DisplayName("Heavier than height list test")
    public void streamNumberOfAnimalsHeavierThanHeight_shouldReturnList(List<Animal> animals, int counter) {
        assertThat(StreamUtils.streamNumberOfAnimalsHeavierThanHeight(animals)).isEqualTo(counter);
    }

    @Test
    @DisplayName("More than two words name test")
    public void streamAnimalNameMoreThanTwoWords_shouldReturnList() {
        assertThat(StreamUtils.streamAnimalNameMoreThanTwoWords(animals)).isEqualTo(Collections.emptyList());
    }

    @Test
    @DisplayName("Dog higher than num test")
    public void streamIsDogInAnimalListHigherThan_shouldReturnTrue_whenListContainsDogHigherThanMinHeight() {
        assertAll(
            () -> assertTrue(StreamUtils.streamIsDogInAnimalListHigherThan(animals, 100)),
            () -> assertFalse(StreamUtils.streamIsDogInAnimalListHigherThan(animals, 130))
        );
    }

    @Test
    @DisplayName("Sum of animal weights which age is between two nums test")
    public void streamSumOfAnimalWeight_shouldReturnValue() {
        assertThat(StreamUtils.streamSumOfAnimalWeight(animals, 0, 4)).isEqualTo(19);
    }

    @Test
    @DisplayName("Sort by type, than sex, than name test")
    public void streamSortAnimalsByTypeSexName_shouldReturnSortedList() {
        assertTrue(Comparators.isInOrder(
            StreamUtils.streamSortAnimalsByTypeSexName(animals),
            Comparator.comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name)
        ));
    }

    @Test
    @DisplayName("Spiders bites more than dogs test")
    public void streamIsSpidersBitesMoreThanDogs_shouldReturnBoolean() {
        List<Animal> listWithNoDogsAndSpiders = List.of(
            new Animal("Igor", Animal.Type.CAT, Animal.Sex.M, 1, 15, 7, false),
            new Animal("Ira", Animal.Type.CAT, Animal.Sex.F, 1, 15, 7, false)
        );
        List<Animal> list = List.of(
            new Animal("Igor", Animal.Type.DOG, Animal.Sex.M, 1, 30, 7, true),
            new Animal("Ilya", Animal.Type.DOG, Animal.Sex.M, 1, 30, 7, true),
            new Animal("Ira", Animal.Type.SPIDER, Animal.Sex.F, 1, 15, 2, true)
        );
        assertAll(
            () -> assertTrue(StreamUtils.streamIsSpidersBitesMoreThanDogs(animals)),
            () -> assertFalse(StreamUtils.streamIsSpidersBitesMoreThanDogs(listWithNoDogsAndSpiders)),
            () -> assertFalse(StreamUtils.streamIsSpidersBitesMoreThanDogs(list))
        );
    }

    @Test
    @DisplayName("Most heavy fish in lists test")
    public void streamMostHeavyFishInLists_shouldReturnAnimal() {
        List<Animal> list = List.of(
            new Animal("Igor", Animal.Type.FISH, Animal.Sex.M, 1, 14, 12, true),
            new Animal("Ilya", Animal.Type.FISH, Animal.Sex.M, 1, 15, 7, true)
        );
        assertThat(StreamUtils.streamMostHeavyFishInLists(animals, list)).isEqualTo(new Animal(
            "Igor",
            Animal.Type.FISH,
            Animal.Sex.M,
            1,
            14,
            12,
            true
        ));
    }

    public static Stream<Arguments> validationErrors() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Animal("Alisa", Animal.Type.DOG, Animal.Sex.F,
                        11, -120, 36, false
                    ),
                    new Animal("Grisha", Animal.Type.DOG, Animal.Sex.M,
                        10, 103, 20, true
                    ),
                    new Animal("Gena", Animal.Type.CAT, Animal.Sex.M,
                        4, 50, 13, true
                    )
                ),
                Map.of(
                    "Alisa", Set.of(new ValidationError("Height can't be below zero!", "height"))
                )
            ),
            Arguments.of(
                List.of(
                    new Animal("Alisa", Animal.Type.DOG, Animal.Sex.F,
                        11, -120, 36, false
                    ),
                    new Animal("Grisha", Animal.Type.DOG, Animal.Sex.M,
                        -10, 103, -20, true
                    ),
                    new Animal("Gena", Animal.Type.CAT, Animal.Sex.M,
                        -4, -50, -13, true
                    )
                ),
                Map.of(
                    "Alisa", Set.of(new ValidationError("Height can't be below zero!", "height")),
                    "Grisha", Set.of(
                        new ValidationError("Age can't be below zero!", "age"),
                        new ValidationError("Weight can't be below zero!", "weight")
                    ),
                    "Gena", Set.of(
                        new ValidationError("Age can't be below zero!", "age"),
                        new ValidationError("Height can't be below zero!", "height"),
                        new ValidationError("Weight can't be below zero!", "weight")
                    )
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("validationErrors")
    @DisplayName("ValidationError test")
    public void streamOfValidationExceptions_shouldReturnMapOfStringAndErrors(
        List<Animal> animals,
        Map<String, Set<ValidationError>> errors
    ) {
        assertThat(StreamUtils.streamOfValidationExceptions(animals)).isEqualTo(errors);
    }

    public static Stream<Arguments> validationErrorsPrettier() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Animal("Alisa", Animal.Type.DOG, Animal.Sex.F,
                        11, -120, 36, false
                    ),
                    new Animal("Grisha", Animal.Type.DOG, Animal.Sex.M,
                        10, 103, 20, true
                    ),
                    new Animal("Gena", Animal.Type.CAT, Animal.Sex.M,
                        4, 50, 13, true
                    )
                ),
                Map.of(
                    "Alisa", "height")
            ),
            Arguments.of(
                List.of(
                    new Animal("Alisa", Animal.Type.DOG, Animal.Sex.F,
                        11, -120, 36, false
                    ),
                    new Animal("Grisha", Animal.Type.DOG, Animal.Sex.M,
                        -10, 103, -20, true
                    ),
                    new Animal("Gena", Animal.Type.CAT, Animal.Sex.M,
                        -4, -50, -13, true
                    )
                ),
                Map.of(
                    "Alisa", "height",
                    "Grisha", "age, weight",
                    "Gena", "age, height, weight"
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("validationErrorsPrettier")
    @DisplayName("Prettier ValidationError test")
    public void streamOfValidationExceptionsPrettier_should(
        List<Animal> animals,
        Map<String, String> errors
    ) {
        assertThat(StreamUtils.streamOfValidationExceptionsPrettier(animals)).isEqualTo(errors);
    }
}

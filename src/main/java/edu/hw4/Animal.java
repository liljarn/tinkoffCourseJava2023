package edu.hw4;

import java.util.HashSet;
import java.util.Set;

public record Animal(
    String name,
    Type type,
    Sex sex,
    int age,
    int height,
    int weight,
    boolean bites
) {
    private static final int CAT_AND_DOGS_PAWS = 4;
    private static final int BIRD_PAWS = 2;
    private static final int FISH_PAWS = 0;
    private static final int SPIDER_PAWS = 8;

    enum Type {
        CAT, DOG, BIRD, FISH, SPIDER
    }

    enum Sex {
        M, F
    }

    public int paws() {
        return switch (type) {
            case CAT, DOG -> CAT_AND_DOGS_PAWS;
            case BIRD -> BIRD_PAWS;
            case FISH -> FISH_PAWS;
            case SPIDER -> SPIDER_PAWS;
        };
    }

    public Set<ValidationError> validation() {
        Set<ValidationError> setOfErrors = new HashSet<>();
        if (age <= 0) {
            setOfErrors.add(new ValidationError("Age can't be below zero!", "age"));
        }
        if (height <= 0) {
            setOfErrors.add(new ValidationError("Height can't be below zero!", "height"));
        }
        if (weight <= 0) {
            setOfErrors.add(new ValidationError("Weight can't be below zero!", "weight"));
        }
        return setOfErrors;
    }
}

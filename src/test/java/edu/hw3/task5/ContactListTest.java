package edu.hw3.task5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import static edu.hw3.task5.ContactList.parseContact;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ContactListTest {
    private static Stream<Arguments> basicTestInputs() {
        return Stream.of(
            Arguments.of(
                new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"}, "ASC",
                List.of(
                    new Contact("Thomas", "Aquinas"),
                    new Contact("Rene", "Descartes"),
                    new Contact("David", "Hume"),
                    new Contact("John", "Locke")
                )
            ),
            Arguments.of(new String[] {"Paul", "Leonhard", "Carl"}, "DESC",
                List.of(
                    new Contact("Paul"),
                    new Contact("Leonhard"),
                    new Contact("Carl")
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("basicTestInputs")
    @DisplayName("List of correct names with and without surnames test")
    public void parseContact_shouldReturnSortedListOfContactsInRightOrder(
        String[] testPeopleInfo,
        String sortOrder,
        List<Contact> expected
    ) {
        assertThat(parseContact(testPeopleInfo, sortOrder)).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Empty or null string array test")
    public void parseContact_shouldReturnEmptyList_whenInputIsNullOrEmpty(String[] emptyStringArray) {
        assertThat(parseContact(emptyStringArray, "ASC")).isEqualTo(Collections.emptyList());
    }

    @Test
    @DisplayName("Wrong name test")
    public void parseContact_shouldThrowException_whenNameIsIncorrect() {
        String[] names = {"Ivan Zolo2004", "Anton Hunter"};
        assertThatThrownBy(()->parseContact(names, "DESC")).isInstanceOf(IllegalArgumentException.class);
    }
}

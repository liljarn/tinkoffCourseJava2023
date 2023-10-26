package edu.hw3.task8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import static org.assertj.core.api.Assertions.assertThat;

public class BackwardIteratorTest {
    @Test
    @DisplayName("List Integers test")
    public void backwardIterator_shouldAddListItemsToListInReversedOrder() {
        Iterator<Integer> iterator = new BackwardIterator<>(List.of(1, 2, 3));
        List<Integer> actual = new ArrayList<>();
        while (iterator.hasNext()) {
            actual.add(iterator.next());
        }
        assertThat(actual).containsExactly(3, 2, 1);
    }

    @Test
    @DisplayName("Stack Characters test")
    public void backwardIterator_shouldAddStackItemsToListInReversedOrder() {
        Stack<Character> stack = new Stack<>();
        List<Character> actual = new ArrayList<>();
        stack.push('a');
        stack.push('b');
        stack.push('c');
        Iterator<Character> iterator = new BackwardIterator<>(stack);
        while (iterator.hasNext()) {
            actual.add(iterator.next());
        }
        assertThat(actual).containsExactly('c', 'b', 'a');
    }

    @Test
    @DisplayName("Queue Strings test")
    public void backwardIterator_shouldAddQueueItemsToListInrReversedOrder() {
        Queue<String> queue = new ArrayDeque<>();
        List<String> actual = new ArrayList<>();
        queue.add("ao");
        queue.add("bo");
        queue.add("co");
        Iterator<String> iterator = new BackwardIterator<>(queue);
        while (iterator.hasNext()) {
            actual.add(iterator.next());
        }
        assertThat(actual).containsExactly("co", "bo", "ao");
    }
}

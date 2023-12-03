package edu.hw3.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public final class BracketsClusterer {
    private BracketsClusterer() {
    }

    public static List<String> clusterize(String brackets) {
        if (brackets == null || brackets.isEmpty()) {
            throw new IllegalArgumentException("Null or empty string");
        }
        List<String> clusters = new ArrayList<>();
        Stack<Character> stack = new Stack<>();
        StringBuilder currentCluster = new StringBuilder();
        for (int i = 0; i < brackets.length(); i++) {
            if (brackets.charAt(i) != '(' && brackets.charAt(i) != ')') {
                throw new IllegalArgumentException("String with wrong character");
            }
            if (brackets.charAt(i) == '(') {
                stack.push(brackets.charAt(i));
            }
            if (brackets.charAt(i) == ')' && !stack.isEmpty()) {
                stack.pop();
            }
            currentCluster.append(brackets.charAt(i));
            if (stack.isEmpty()) {
                clusters.add(currentCluster.toString());
                currentCluster = new StringBuilder();
            }
        }
        if (!stack.isEmpty()) {
            throw new IllegalArgumentException("Not balanced");
        }
        return clusters;
    }
}

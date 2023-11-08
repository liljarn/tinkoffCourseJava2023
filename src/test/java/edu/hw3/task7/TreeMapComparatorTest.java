package edu.hw3.task7;

import java.util.Map;
import java.util.TreeMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import static org.assertj.core.api.Assertions.assertThat;
public class TreeMapComparatorTest {

    @ParameterizedTest
    @NullSource
    @DisplayName("Put null as key test")
    public void nullableKey_shouldBeInTreeMap(String nullKey) {
        Map<String, String> treeMap = new TreeMap<>(new TreeMapComparator());
        treeMap.put("aboba", "zxc");
        treeMap.put("in", "zxc");
        treeMap.put("out", "zxc");
        treeMap.put(nullKey, "test");
        assertThat(treeMap.containsKey(null)).isTrue();
    }
}

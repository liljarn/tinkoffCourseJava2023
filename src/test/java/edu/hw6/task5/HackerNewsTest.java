package edu.hw6.task5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static edu.hw6.task5.HackerNews.news;
import static org.assertj.core.api.Assertions.assertThat;

public class HackerNewsTest {
    @Test
    @DisplayName("hackerNewsTopStories method test")
    public void testHackerNewsTopStoriesHTTPResponseCode() {
        long[] topStories = HackerNews.hackerNewsTopStories();
        assertThat(topStories).isNotEmpty();
    }

    private static Stream<Arguments> newsForTest() {
        return Stream.of(
            Arguments.of(37570037L, "JDK 21 Release Notes"),
            Arguments.of(38233773L, "Installing OpenBSD on the Fastest iMac G3 [video]"),
            Arguments.of(38234689L, "A Rusty CHERI – The path to hardware capabilities in Rust [video]"),
            Arguments.of(38250284L, "Connections with James Burke is back"),
            Arguments.of(38222909L, "The Unix timestamp will begin with 17 this Tuesday")
        );
    }

    @ParameterizedTest
    @MethodSource("newsForTest")
    @DisplayName("news method test")
    public void news_shouldReturnStringOfTitleOfNewsFromJson(long id, String title) {
        assertThat(news(id)).isEqualTo(title);
    }

    @Test
    @DisplayName("news method with wrong id test")
    public void news_shouldReturnNull() {
        assertThat(news(-6)).isEmpty();
    }
}

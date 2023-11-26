package edu.hw6.task5;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class HackerNews {
    private HackerNews() {
    }

    public static long[] hackerNewsTopStories() {
        HttpRequest request =
            HttpRequest.newBuilder(URI.create("https://hacker-news.firebaseio.com/v0/topstories.json")).build();

        try (HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build()) {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return convertStringToLongArray(response.body());
        } catch (IOException | InterruptedException e) {
            return new long[0];
        }
    }

    private static long[] convertStringToLongArray(String responseBody) {
        String str = responseBody.substring(1, responseBody.length() - 1);
        String[] codes = str.split(",");
        return Arrays.stream(codes).mapToLong(Long::parseLong).toArray();
    }

    public static String news(long id) {
        HttpRequest request =
            HttpRequest.newBuilder(URI.create(
                "https://hacker-news.firebaseio.com/v0/item/" + id + ".json")).build();
        try (HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build()) {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return parseNews(response.body());
        } catch (IOException | InterruptedException e) {
            return "";
        }
    }

    private static String parseNews(String newsJson) {
        Pattern pattern = Pattern.compile("\"title\":\"([^\"]+)\"");
        Matcher matcher = pattern.matcher(newsJson);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }
}

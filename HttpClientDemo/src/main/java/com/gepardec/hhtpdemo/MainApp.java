package com.gepardec.hhtpdemo;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.function.Function;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MainApp {
    public static void main(String[] args) throws Exception {
        System.setProperty("jdk.internal.httpclient.debug", "true");

        demoSyncRequest();
        demoAsyncRequest();
    }

    private static void demoSyncRequest() throws Exception {
        var httpRequest = HttpRequest.newBuilder(URI.create("http://lovecalculator.lion.enterprises/getPercentage.php?fname=Markus&sname=Martina"))
                .header("API-Key", "magic-key-42")
                .version(HttpClient.Version.HTTP_2)
                .GET()
                .build();

        var httpResponse = HttpClient.newHttpClient().send(httpRequest, BodyHandlers.ofString());

        printResponse(httpResponse);
    }

    private static void demoAsyncRequest() throws Exception {
        var httpRequest = HttpRequest.newBuilder(new URI("http://lovecalculator.lion.enterprises/getPercentage.php?fname=Markus&sname=Martina"))
                .header("API-Key", "magic-key-42")
                .version(HttpClient.Version.HTTP_2)
                .GET()
                .build();

        HttpClient.newHttpClient().sendAsync(httpRequest, BodyHandlers.ofString())
                .thenApply(printResponseAndExtractBody())
                .thenApply(convertJsonToObject())
                .thenAccept(System.out::println)
                .join();   // blocks
    }

    private static Function<HttpResponse<String>, String> printResponseAndExtractBody() {
        return httpResponse -> {
            printResponse(httpResponse);
            return httpResponse.body();
        };
    }

    private static Function<String, LoveCalculatorResult> convertJsonToObject() {
        return body -> {
            try {
                var om = new ObjectMapper();
                return om.readValue(body, LoveCalculatorResult.class);
            }
            catch (Exception e) {
                throw new RuntimeException("can't convert JSON to object: " + e.getMessage(), e);
            }
        };
    }

    private static void printResponse(HttpResponse<?> httpResponse) {
        System.out.println(
                "status code: " + httpResponse.statusCode() + System.lineSeparator() +
                        "headers: " + httpResponse.headers() + System.lineSeparator() +
                        "body: " + httpResponse.body()
        );
    }
}
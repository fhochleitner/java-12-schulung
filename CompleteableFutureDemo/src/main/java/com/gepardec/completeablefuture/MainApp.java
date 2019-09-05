package com.gepardec.completeablefuture;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

// see https://www.baeldung.com/java-completablefuture

public class MainApp {
    private static final boolean ACTIVATE_DELAY = true;   // alternative: true
    public static void main(String[] args) throws Exception {


        Integer a = 3;
        Integer b = 5;   // alternative: null

        //Task 1: soll asynchron das Produkt berechnen, vorher prüfen ob a/b ungleich null -> sonst exception.
        //Ergebnis als String geliefert werden, z.B. "15 calculated by task x)

        //Task 2: soll asynchron das Produkt berechnen, vorher prüfen ob a/b ungleich null -> sonst exception.
        //Ergebnis als String geliefert werden, z.B. "15 calculated by task x)

        //beide Tasks aufrufen und schneller berechnetes Ergebnis anzeigen

        CompletableFuture<String> productfuture = CompletableFuture.supplyAsync(() -> {
            Objects.requireNonNull(a, "a is null");
            Objects.requireNonNull(b, "b is null");
//            delay(1200L);
            return String.valueOf(a * b + " Produktberechnung");
        }).orTimeout(1, TimeUnit.SECONDS);
        CompletableFuture<String> additionFuture = CompletableFuture.supplyAsync(() -> {
            Objects.requireNonNull(a, "a is null");
            Objects.requireNonNull(b, "b is null");
//            delay(1000L);
            return String.valueOf(a + b + " Addition");
        }).orTimeout(1, TimeUnit.SECONDS);

        CompletableFuture.anyOf(productfuture, additionFuture).exceptionally(throwable -> "no result: (" + throwable.getMessage() + ")" ).thenAccept(System.out::println);


        ForkJoinPool.commonPool().awaitQuiescence(1, TimeUnit.MINUTES);
    }

    private static void delay(long millis) {
        if (ACTIVATE_DELAY) {
            try {
                Thread.sleep(millis);
            }
            catch (InterruptedException ignored) {}
        }
    }
}
package com.gepardec.flow;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

public class MainApp {

    public static void main(String[] args) {

        var jokeProvider = new SubmissionPublisher<String>();
        jokeProvider.subscribe(new SimpleSubscriber());
        JOKES.forEach(jokeProvider::submit);
        jokeProvider.close();
        ForkJoinPool.commonPool().awaitQuiescence(1, TimeUnit.MINUTES);

    }

    private static final List<String> JOKES = List.of(
            "Chuck Norris hat als Kind Sandburgen gebaut. Wir kennen sie heute als Pyramiden.",
            "Was ist der Unterschied zwischen Batman und Microsoft? - Batman hat den Pinguin besiegt.",
            "Chuck Norris kennt die letzte Ziffer von Pi.",
            "Was ist flach und eckig? – Ein Minecraft-Witz.",
            "Chuck Norris ist Darth Vaders Vater.",
            "Chuck Norris hat es einmal geschafft ein zwei Kilo Steak in 30 Minuten zu essen. In den ersten 25 Minuten hatte er allerdings Sex mit der Kellnerin."
    );

}

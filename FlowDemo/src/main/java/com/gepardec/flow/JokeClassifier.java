package com.gepardec.flow;

public class JokeClassifier {

    public Joke createJoke(String jokeString) {

        var joke = new Joke();
        joke.setJoke(jokeString);
        joke.setCategory(classifyCategory(jokeString));
        joke.setClean(classifyClean(jokeString));
        return joke;
    }

    private boolean classifyClean(String jokeString) {

        return !jokeString.contains("Sex");
    }

    private Category classifyCategory(String jokeString) {

        return jokeString.contains("Chuck Norris") ? Category.CHUCK_NORRIS : Category.OTHER;
    }

}

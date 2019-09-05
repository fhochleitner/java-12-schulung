    package com.gepardec.flow;

    import java.util.concurrent.Flow;

    public class SimpleSubscriber implements Flow.Subscriber<String> {

        private Flow.Subscription subscription;
        private JokeClassifier classifier =  new JokeClassifier();

        @Override public void onSubscribe(Flow.Subscription subscription) {

            this.subscription = subscription;
            subscription.request(1);
        }

        @Override public void onNext(String item) {

            Joke joke = classifier.createJoke(item);
            if (joke.isClean()) {
                System.out.println(joke.getJoke());
            } else {
                System.err.println(joke.getJoke());
            }

            subscription.request(1);
        }

        @Override public void onError(Throwable throwable) {

        }

        @Override public void onComplete() {

        }
    }

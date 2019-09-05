package com.gepardec.stream;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MainApp {

    public static void main(String[] args) {
        var persons = List.of(
                new Person(1L, "Markus", "Mustermann"),
                new Person(2L, "Anita", "Auerbach"),
                new Person(3L, "Martina", "Musterfrau"),
                new Person(4L, "Zacharias", "Zeppelin"),
                new Person(5L, "Maxi", "Musterkind")
        );

//        var personList = persons.stream().sorted(Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName)).collect(
//                Collectors.toList());
//        personList.forEach(System.out::println);
//

        persons.stream().sorted(Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName))
                .dropWhile(person -> !person.getLastName().startsWith("Muster"))
                .takeWhile(person -> person.getLastName().startsWith("Muster"))
                .forEach(System.out::println);
    }
}

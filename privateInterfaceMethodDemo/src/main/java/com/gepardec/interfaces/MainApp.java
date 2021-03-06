package com.gepardec.interfaces;

import com.gepardec.interfaces.Chiffre;

import java.util.List;

public class MainApp {

    private static final List<String> MESSAGES = List.of("Hello World", "Simpler Plaintext", "SecretSauce");

    public static void main(String[] args) {
        var aesChriffre = Chiffre.withSecretKey("blubb");
        System.out.println("Plaintexts: ");
        MESSAGES.forEach(s -> System.out.println(s));

        System.out.println("-------------------");
        System.out.println("Encrypted: ");

        System.out.println(aesChriffre.encryptAll(MESSAGES));

    }

}
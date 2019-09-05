package com.gepardec.interfaces;

import com.gepardec.AESChiffre;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface Chiffre {

    /**
     * Hier wird die Implementierung in das Interface hereingezogen. Nicht Optimal!!!
     * @param secretKey
     * @return
     */
    static Chiffre withSecretKey(String secretKey){
        return new AESChiffre(secretKey);
    }

    String encrypt(String plaintext);

    String decrypt(String ciphertext);

    default List<String> encryptAll(List<String> plaintexts) {
        return processAll(plaintexts, this::encrypt);
    }

    default List<String> decryptAll(List<String> ciphertext) {

        return processAll(ciphertext, this::decrypt);
    }

    private List<String> processAll(List<String> texts, Function<String, String> function){
        return texts.stream().map(function).collect(Collectors.toList());
    }

}

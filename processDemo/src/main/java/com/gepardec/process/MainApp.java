package com.gepardec.process;

import java.io.BufferedReader;
import java.io.IOException;

public class MainApp {

    public static void main(String[] args) throws IOException, InterruptedException {

        ProcessHandle.allProcesses().map(p -> p.info().command()).filter(command -> command.isPresent())
                .forEach(command -> System.out.println(command));

        System.out.println("\n-------------------------------------\n");
        ProcessBuilder pb = new ProcessBuilder("java", "-version");
        pb.redirectErrorStream(true);
        var process = pb.start();
        process.getInputStream().transferTo(System.out);
        process.waitFor();
        process.onExit().thenAccept(p -> System.out.println("Prozess mit Wert : " + p.exitValue() + " beendet"));

    }
}

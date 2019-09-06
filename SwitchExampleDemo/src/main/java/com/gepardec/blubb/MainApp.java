package com.gepardec.blubb;

public class MainApp {

   private static  Card blubb = new Card(Suit.CLUBS, Rank.ACE);

    public static void main(String[] args) {

        System.out.println(blubb.getDescription());
    }

}

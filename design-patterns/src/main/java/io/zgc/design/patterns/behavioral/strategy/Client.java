package io.zgc.design.patterns.behavioral.strategy;

public class Client {

    public static void main(String[] args) {

        TeamGNR gnr = new TeamGNR();

        gnr.setGameStrategy(new RandomStrategy());
        gnr.startGame();
    }
}

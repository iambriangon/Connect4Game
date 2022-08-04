package com.company;
import java.util.Scanner;

public class Game {
    private final Board b;
    private final Player p1;
    private final Player p2;
    private int round = 1;

    public Game(){
        b = new Board();
        p1 = new Player("Player1", 'O');
        p2 = new Player("Player2", 'X');
    }

    private char getToken(){
        if (round == 1){
            return p1.getToken();
        }
        else {
            return p2.getToken();
        }
    }

    private String getPlayerTurn(){
        return round==1? p1.getName() : p2.getName();
    }

    private void nextRound(){
        round *= -1;
    }

    private void checkPlayerTokens(){
        System.out.println(p1.getName() + " " + "has token: "+ p1.getToken());
        System.out.println(p2.getName() + " " + "has token: "+ p2.getToken());
        System.out.println("________________________________\n");
    }

    public void play(){
        checkPlayerTokens();
        System.out.println("Initial Board");
        b.showBoard();
        int column;
        while(!b.isFullorFinished()){
            Scanner sc = new Scanner(System.in);
            System.out.print("\nChoose column [1 - 7] " + getPlayerTurn() + ":\n");
            column = sc.nextInt() - 1;
            if (b.insert(column, getToken())){
                b.showBoard();
                nextRound();
            }
        }
        b.getFinalMessage(getPlayerTurn());
    }
}

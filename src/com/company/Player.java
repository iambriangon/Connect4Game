package com.company;

public class Player {
    private String name;
    private char token;

    public Player(String name, char token){
        this.name = name;
        this.token = token;
    }

    public char getToken() {
        return token;
    }

    public String getName() {
        return name;
    }
}

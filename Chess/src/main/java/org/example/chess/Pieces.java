package org.example.chess;

import javafx.scene.Node;
import javafx.scene.control.Button;

import java.util.ArrayList;

public class Pieces {
    private final Button button;
    String name;
    private String position;
    private ArrayList<String> legalMoves = new ArrayList<>();
    private boolean canBeCaptured;
    private boolean isAlive;

    public Pieces(Button b, int pos1, int pos2){
        int[] pos = {pos1, pos2};

        this.button = b;
        this.position = convertPos(pos);
        if(this.position.charAt(1) == '7'){
            this.name = "Pawn";
        }

    }

    public Node toNode(){
        return this.button;
    }

    public String convertPos(int[] pos){
        String position = "";
        switch (pos[0]){
            case 0 -> position += "a";
            case 1 -> position += "b";
            case 2 -> position += "c";
            case 3 -> position += "d";
            case 4 -> position += "e";
            case 5 -> position += "f";
            case 6 -> position += "g";
            case 7 -> position += "h";
        }

        switch (pos[1]){
            case 0 -> position += "1";
            case 1 -> position += "2";
            case 2 -> position += "3";
            case 3 -> position += "4";
            case 4 -> position += "5";
            case 5 -> position += "6";
            case 6 -> position += "7";
            case 7 -> position += "8";
        }
        return position;
    }
}

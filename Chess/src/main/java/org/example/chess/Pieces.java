package org.example.chess;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Pieces {
    private Button button;
    String name;
    private String position;
    private static ArrayList<String> legalMoves = new ArrayList<>();
    private boolean canBeCaptured;
    private boolean isAlive;
    private boolean isDame;
    private Board board;


    public Pieces(){}

    public Pieces(Board board, Button b, int pos1, int pos2, boolean dame){
        int[] pos = {pos1, pos2};

        this.board = board;
        this.button = b;
        this.isDame = dame;
        this.position = posToString(pos);

        if(!this.isDame){
            if(this.position.charAt(1) == '7'){
                ImageView blackPawn = new ImageView(getClass().getResource("img/blackPawn.png").toExternalForm());
                this.name = "blackPawn";
                this.button.setGraphic(blackPawn);
            }
            else if(this.position.charAt(1) == '2'){
                ImageView whitePawn = new ImageView(getClass().getResource("img/whitePawn.png").toExternalForm());
                this.name = "whitePawn";
                this.button.setGraphic(whitePawn);
            }
            else if(this.position.equals("a8") || this.position.equals("h8")){
                this.name = "blackRook";
                ImageView blackRook = new ImageView(getClass().getResource("img/blackRook.png").toExternalForm());
                this.button.setGraphic(blackRook);
            }
            else if(this.position.equals("a1") || this.position.equals("h1")){
                this.name = "whiteRook";
                ImageView whiteRook = new ImageView(getClass().getResource("img/whiteRook.png").toExternalForm());
                this.button.setGraphic(whiteRook);
            }

            else if(this.position.equals("b8") || this.position.equals("g8")){
                this.name = "blackKnight";
                ImageView blackKnight = new ImageView(getClass().getResource("img/blackKnight.png").toExternalForm());
                this.button.setGraphic(blackKnight);
            }
            else if(this.position.equals("b1") || this.position.equals("g1")) {
                this.name = "whiteKnight";
                ImageView whiteKnight = new ImageView(getClass().getResource("img/whiteKnight.png").toExternalForm());
                this.button.setGraphic(whiteKnight);
            }

            else if(this.position.equals("c8") || this.position.equals("f8")){
                this.name = "blackBishop";
                ImageView blackBishop = new ImageView(getClass().getResource("img/blackBishop.png").toExternalForm());
                this.button.setGraphic(blackBishop);
            }
            else if(this.position.equals("c1") || this.position.equals("f1")){
                this.name = "whiteBishop";
                ImageView whiteBishop = new ImageView(getClass().getResource("img/whiteBishop.png").toExternalForm());
                this.button.setGraphic(whiteBishop);
            }

            else if(this.position.equals("d8")){
                this.name = "blackQueen";
                ImageView blackQueen = new ImageView(getClass().getResource("img/blackQueen.png").toExternalForm());
                this.button.setGraphic(blackQueen);
            }
            else if(this.position.equals("d1")){
                this.name = "whiteQueen";
                ImageView whiteQueen = new ImageView(getClass().getResource("img/whiteQueen.png").toExternalForm());
                this.button.setGraphic(whiteQueen);
            }

            else if(this.position.equals("e8")){
                this.name = "blackKing";
                ImageView blackKing = new ImageView(getClass().getResource("img/blackKing.png").toExternalForm());
                this.button.setGraphic(blackKing);
            }
            else if(this.position.equals("e1")){
                this.name = "whiteKing";
                ImageView whiteKing = new ImageView(getClass().getResource("img/whiteKing.png").toExternalForm());
                this.button.setGraphic(whiteKing);
            }

            else{
                this.name = "";
            }
        }

        this.button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                canSwap();
            }
        });
    }

    public void innitLegalMoves(){
        if(this.name.equals("blackPawn")){
            for(int i = 1; i < 3; i++){
                int[] pos = stringToPos(this.position);
                try{
                    if(this.board.gridGame[pos[0]][pos[1] + i].name.isEmpty()){
                        legalMoves.add(this.board.gridGame[pos[0]][pos[1] + i].position);
                    }
                }
                catch(Exception ignored){}
            }
        }
        displayLegalMoves(legalMoves);
    }

    public void canSwap(){
        if(!this.name.isEmpty()){
            legalMoves.clear();
            innitLegalMoves();
            this.board.chosen = this;
        }
        else{
            if(!(this.board.chosen == null)){
                for(String move : legalMoves){
                    if(this.position.equals(move)){
                        swap(this, this.board.chosen);
                    }
                }
            }
        }
    }

    public void swap(Pieces p1, Pieces p2){
        Pieces temp = new Pieces();
        temp.name = "";
        temp.button = new Button();

        temp.name = p1.name;
        p1.name = p2.name;
        p2.name = temp.name;

        temp.position = p1.position;
        p1.position = p2.position;
        p2.position = temp.position;

        temp.button.setGraphic(p1.button.getGraphic());
        p1.button.setGraphic(p2.button.getGraphic());
        p2.button.setGraphic(temp.button.getGraphic());
    }


    public Node toNode(){
        return this.button;
    }

    public String posToString(int[] pos){
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
            case 0 -> position += "8";
            case 1 -> position += "7";
            case 2 -> position += "6";
            case 3 -> position += "5";
            case 4 -> position += "4";
            case 5 -> position += "3";
            case 6 -> position += "2";
            case 7 -> position += "1";
        }
        return position;
    }

    public int[] stringToPos(String position){
        int[] pos = new int[2];
        switch (position.charAt(0)){
            case 'a' -> pos[0] = 0;
            case 'b' -> pos[0] = 1;
            case 'c' -> pos[0] = 2;
            case 'd' -> pos[0] = 3;
            case 'e' -> pos[0] = 4;
            case 'f' -> pos[0] = 5;
            case 'g' -> pos[0] = 6;
            case 'h' -> pos[0] = 7;
        }

        switch (position.charAt(1)){
            case '8' -> pos[1] = 0;
            case '7' -> pos[1] = 1;
            case '6' -> pos[1] = 2;
            case '5' -> pos[1] = 3;
            case '4' -> pos[1] = 4;
            case '3' -> pos[1] = 5;
            case '2' -> pos[1] = 6;
            case '1' -> pos[1] = 7;
        }
        return pos;
    }

    public void displayLegalMoves(ArrayList<String> array){
        for(String arr : array){
            System.out.println(arr);
        }
    }
}

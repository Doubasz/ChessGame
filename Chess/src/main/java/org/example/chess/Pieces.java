package org.example.chess;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.security.spec.ECField;
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
    private boolean isDebut;
    int nbPieceAhead = 0;


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
                this.isDebut = true;
            }
            else if(this.position.charAt(1) == '2'){
                ImageView whitePawn = new ImageView(getClass().getResource("img/whitePawn.png").toExternalForm());
                this.name = "whitePawn";
                this.button.setGraphic(whitePawn);
                this.isDebut = true;
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
        legalMoves.clear();
        int[] pos = stringToPos(this.position);

        if(this.name.contains(this.board.currentPlayer)) {


            //Initialising the legal moves of pawns
            if (this.name.equals("blackPawn")) {

                //Moving forward with a Pawn
                if (this.isDebut) {
                    for (int i = 1; i < 3; i++) {
                        try {
                            if (this.board.gridGame[pos[0]][pos[1] + i].name.isEmpty()) {
                                legalMoves.add(this.board.gridGame[pos[0]][pos[1] + i].position);
                            }
                        } catch (Exception ignored) {
                        }
                    }
                    this.isDebut = false;
                } else {
                    try {
                        if (this.board.gridGame[pos[0]][pos[1] + 1].name.isEmpty()) {
                            legalMoves.add(this.board.gridGame[pos[0]][pos[1] + 1].position);
                        }
                    } catch (Exception ignored) {
                    }
                }

                //Capturing with the Pawn
                try {
                    if (!this.board.gridGame[pos[0] + 1][pos[1] + 1].name.isEmpty() && this.board.gridGame[pos[0] + 1][pos[1] + 1].name.contains("white")) {
                        this.board.gridGame[pos[0] + 1][pos[1] + 1].canBeCaptured = true;
                        legalMoves.add(this.board.gridGame[pos[0] + 1][pos[1] + 1].position);
                    }
                    if (!this.board.gridGame[pos[0] - 1][pos[1] + 1].name.isEmpty() && this.board.gridGame[pos[0] - 1][pos[1] + 1].name.contains("white")) {
                        this.board.gridGame[pos[0] - 1][pos[1] + 1].canBeCaptured = true;
                        legalMoves.add(this.board.gridGame[pos[0] - 1][pos[1] + 1].position);
                    }
                } catch (Exception ignored) {
                }

            } else if (this.name.equals("whitePawn")) {

                //Moving forward with a Pawn
                if (this.isDebut) {
                    for (int i = 1; i < 3; i++) {
                        pos = stringToPos(this.position);
                        try {
                            if (this.board.gridGame[pos[0]][pos[1] - i].name.isEmpty()) {
                                legalMoves.add(this.board.gridGame[pos[0]][pos[1] - i].position);
                            }
                        } catch (Exception ignored) {
                        }
                    }
                    this.isDebut = false;
                } else {
                    pos = stringToPos(this.position);
                    try {
                        if (this.board.gridGame[pos[0]][pos[1] - 1].name.isEmpty()) {
                            legalMoves.add(this.board.gridGame[pos[0]][pos[1] - 1].position);
                        }
                    } catch (Exception ignored) {
                    }
                }

                //Capturing with the pawn
                try {
                    if (!this.board.gridGame[pos[0] - 1][pos[1] - 1].name.isEmpty() && this.board.gridGame[pos[0] - 1][pos[1] - 1].name.contains("black")) {
                        this.board.gridGame[pos[0] - 1][pos[1] - 1].canBeCaptured = true;
                        legalMoves.add(this.board.gridGame[pos[0] - 1][pos[1] - 1].position);
                    }
                } catch (Exception ignored) {
                }
                try {
                    if (!this.board.gridGame[pos[0] + 1][pos[1] - 1].name.isEmpty() && this.board.gridGame[pos[0] + 1][pos[1] - 1].name.contains("black")) {
                        this.board.gridGame[pos[0] + 1][pos[1] - 1].canBeCaptured = true;
                        legalMoves.add(this.board.gridGame[pos[0] + 1][pos[1] - 1].position);
                    }
                } catch (Exception ignored) {
                }
            }

            //Initializing the moves of knights

            else if (this.name.contains("Knight")) {
                try {
                    legalMovesPieces(pos[0] + 1, pos[1] - 2);
                } catch (Exception ignored) {}
                try {
                    legalMovesPieces(pos[0] + 1, pos[1] + 2);
                } catch (Exception ignored) {}
                try {
                    legalMovesPieces(pos[0] - 1, pos[1] - 2);
                } catch (Exception ignored) {}
                try {
                    legalMovesPieces(pos[0] - 1, pos[1] + 2);
                } catch (Exception ignored) {}
                try {
                    legalMovesPieces(pos[0] - 2, pos[1] + 1);
                } catch (Exception ignored) {
                }
                try {
                    legalMovesPieces(pos[0] - 2, pos[1] - 1);
                } catch (Exception ignored) {}
                try {
                    legalMovesPieces(pos[0] + 2, pos[1] - 1);
                } catch (Exception ignored) {
                }
                try {
                    legalMovesPieces(pos[0] + 2, pos[1] + 1);
                } catch (Exception ignored) {
                }
            }

            if(this.name.contains("Bishop")){
                this.nbPieceAhead = 0;
                    for(int i = 0; i < 8; i++) {
                        try {
                            legalMovesPieces2(pos[0] + i, pos[1] + i);
                        } catch (Exception ignored) {
                        }
                    }
                    this.nbPieceAhead = 0;
                    for(int i = 0; i < 8; i++) {

                        try {
                            legalMovesPieces2(pos[0] - i, pos[1] + i);
                        } catch (Exception ignored) {
                        }
                    }
                    this.nbPieceAhead = 0;
                    for(int i = 0; i < 8; i++) {

                        try {
                            legalMovesPieces2(pos[0] + i, pos[1] - i);
                        } catch (Exception ignored) {
                        }
                    }
                    this.nbPieceAhead = 0;
                    for(int i = 0; i < 8; i++){
                        try {
                            legalMovesPieces2(pos[0] - i, pos[1] - i);
                        }catch (Exception ignored) {}
                    }
                    this.nbPieceAhead = 0;
            }

            if(this.name.contains("Rook")){
                this.nbPieceAhead = 0;
                for(int i = 0; i < 8; i++) {
                    try {
                        legalMovesPieces2(pos[0] + i, pos[1]);
                    } catch (Exception ignored) {
                    }
                }
                this.nbPieceAhead = 0;
                for(int i = 0; i < 8; i++) {

                    try {
                        legalMovesPieces2(pos[0] - i, pos[1]);
                    } catch (Exception ignored) {
                    }
                }
                this.nbPieceAhead = 0;
                for(int i = 0; i < 8; i++) {
                    try {
                        legalMovesPieces2(pos[0], pos[1] + i);
                    } catch (Exception ignored) {
                    }
                }
                this.nbPieceAhead = 0;
                for(int i = 0; i < 8; i++) {

                    try {
                        legalMovesPieces2(pos[0], pos[1] - i);
                    } catch (Exception ignored) {
                    }
                }
            }
        }
        displayLegalMoves(legalMoves);
    }

    public void canSwap(){
        //When the tile is not empty
        if(!this.name.isEmpty() && !this.canBeCaptured){
            innitLegalMoves();
            this.board.chosen = this;
        }

        //When a piece can be Captured
        else if(this.canBeCaptured){
            for(String move : legalMoves){
                if(this.position.equals(move)){
                    capture(this, this.board.chosen);
                    this.board.changeCurrentPlayer();
                }
            }
            resetCanBeCaptured();
        }

        //When the tile is empty
        else{
            for(String move : legalMoves){
                if(this.position.equals(move)){
                    swap(this.board.chosen, this);
                    this.board.changeCurrentPlayer();
                }
            }
            legalMoves.clear();
        }
    }

    public void swap(Pieces p1, Pieces p2){
        Pieces temp = new Pieces();
        temp.name = "";
        temp.button = new Button();

        temp.name = p1.name;
        p1.name = p2.name;
        p2.name = temp.name;

        temp.button.setGraphic(p1.button.getGraphic());
        p1.button.setGraphic(p2.button.getGraphic());
        p2.button.setGraphic(temp.button.getGraphic());
    }

    public void capture(Pieces captured, Pieces capturing){
        captured.name = capturing.name;
        capturing.name = "";

        captured.button.setGraphic(capturing.button.getGraphic());
        capturing.button.setGraphic(null);
        captured.canBeCaptured = false;
    }

    public void resetCanBeCaptured(){
        for(int i = 0; i < this.board.gridGame.length; i++){
            for(int j = 0; j < this.board.gridGame[0].length; j++){
                this.board.gridGame[i][j].canBeCaptured = false;
            }
        }
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

    public void legalMovesPieces(int num1, int num2){
        if (this.board.gridGame[num1][num2].name.isEmpty()) {
            legalMoves.add(this.board.gridGame[num1][num2].position);
        } else if (this.board.gridGame[num1][num2].name.contains("white") && this.name.contains("black")) {
            this.board.gridGame[num1][num2].canBeCaptured = true;
            legalMoves.add(this.board.gridGame[num1][num2].position);
        } else if (this.board.gridGame[num1][num2].name.contains("black") && this.name.contains("white")) {
            this.board.gridGame[num1][num2].canBeCaptured = true;
            legalMoves.add(this.board.gridGame[num1][num2].position);
        }
    }

    public void legalMovesPieces2(int num1, int num2){
        if(this.board.gridGame[num1][num2].name.isEmpty()) {
            legalMoves.add(this.board.gridGame[num1][num2].position);
        } else if (this.board.gridGame[num1][num2].name.contains("white") && this.name.contains("black")) {
            if(this.nbPieceAhead < 1)
            {
                this.board.gridGame[num1][num2].canBeCaptured = true;
                legalMoves.add(this.board.gridGame[num1][num2].position);
                this.nbPieceAhead++;
            }
        } else if (this.board.gridGame[num1][num2].name.contains("black") && this.name.contains("white")) {
            if(this.nbPieceAhead < 1){
                this.board.gridGame[num1][num2].canBeCaptured = true;
                legalMoves.add(this.board.gridGame[num1][num2].position);
                this.nbPieceAhead++;
            }
        }
    }
}

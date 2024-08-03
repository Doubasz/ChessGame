package org.example.chess;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Objects;

public class Pieces {
    private Button button;
    String name;
    private String position;
    private ArrayList<String> legalMoves = new ArrayList<>();
    private boolean canBeCaptured;
    private boolean isAlive;
    private boolean isDame;
    private Board board;

    boolean controlledByBlack;
    boolean controlledByWhite;
    boolean neverMoved;

    //TODO make so that the king cant capture a protected piece
    //TODO make so that a piece cant move if it lead to the king being in check (no idea how to do it)
    //TODO implement checks and making so that the only legal moves remove the check
    //TODO implement checkmate
    //TODO Store games in a text file for data
    //TODO improve the GUI

    public Pieces() {
    }

    public Pieces(Board board, Button b, int pos1, int pos2, boolean dame) {
        int[] pos = {pos1, pos2};

        this.board = board;
        this.button = b;
        this.isDame = dame;
        this.position = posToString(pos);
        this.controlledByWhite = false;
        this.controlledByBlack = false;
        this.neverMoved = false;

        if (!this.isDame) {
            if (this.position.charAt(1) == '7') {
                ImageView blackPawn = new ImageView(Objects.requireNonNull(getClass().getResource("img/blackPawn.png")).toExternalForm());
                this.name = "blackPawn";
                this.button.setGraphic(blackPawn);
                this.neverMoved = true;
            } else if (this.position.charAt(1) == '2') {
                ImageView whitePawn = new ImageView(Objects.requireNonNull(getClass().getResource("img/whitePawn.png")).toExternalForm());
                this.name = "whitePawn";
                this.button.setGraphic(whitePawn);
                this.neverMoved = true;
            } else if (this.position.equals("a8") || this.position.equals("h8")) {
                this.name = "blackRook";
                ImageView blackRook = new ImageView(Objects.requireNonNull(getClass().getResource("img/blackRook.png")).toExternalForm());
                this.button.setGraphic(blackRook);
                this.neverMoved = true;
            } else if (this.position.equals("a1") || this.position.equals("h1")) {
                this.name = "whiteRook";
                ImageView whiteRook = new ImageView(Objects.requireNonNull(getClass().getResource("img/whiteRook.png")).toExternalForm());
                this.button.setGraphic(whiteRook);
                this.neverMoved = true;
            } else if (this.position.equals("b8") || this.position.equals("g8")) {
                this.name = "blackKnight";
                ImageView blackKnight = new ImageView(Objects.requireNonNull(getClass().getResource("img/blackKnight.png")).toExternalForm());
                this.button.setGraphic(blackKnight);
            } else if (this.position.equals("b1") || this.position.equals("g1")) {
                this.name = "whiteKnight";
                ImageView whiteKnight = new ImageView(Objects.requireNonNull(getClass().getResource("img/whiteKnight.png")).toExternalForm());
                this.button.setGraphic(whiteKnight);
            } else if (this.position.equals("c8") || this.position.equals("f8")) {
                this.name = "blackBishop";
                ImageView blackBishop = new ImageView(Objects.requireNonNull(getClass().getResource("img/blackBishop.png")).toExternalForm());
                this.button.setGraphic(blackBishop);
            } else if (this.position.equals("c1") || this.position.equals("f1")) {
                this.name = "whiteBishop";
                ImageView whiteBishop = new ImageView(Objects.requireNonNull(getClass().getResource("img/whiteBishop.png")).toExternalForm());
                this.button.setGraphic(whiteBishop);
            } else if (this.position.equals("d8")) {
                this.name = "blackQueen";
                ImageView blackQueen = new ImageView(Objects.requireNonNull(getClass().getResource("img/blackQueen.png")).toExternalForm());
                this.button.setGraphic(blackQueen);
            } else if (this.position.equals("d1")) {
                this.name = "whiteQueen";
                ImageView whiteQueen = new ImageView(Objects.requireNonNull(getClass().getResource("img/whiteQueen.png")).toExternalForm());
                this.button.setGraphic(whiteQueen);
            } else if (this.position.equals("e8")) {
                this.name = "blackKing";
                ImageView blackKing = new ImageView(Objects.requireNonNull(getClass().getResource("img/blackKing.png")).toExternalForm());
                this.button.setGraphic(blackKing);
                this.neverMoved = true;
            } else if (this.position.equals("e1")) {
                this.name = "whiteKing";
                ImageView whiteKing = new ImageView(Objects.requireNonNull(getClass().getResource("img/whiteKing.png")).toExternalForm());
                this.button.setGraphic(whiteKing);
                this.neverMoved = true;
            } else {
                this.name = "";
            }
        }

        this.button.setOnAction(actionEvent -> canSwap());
    }

    public void innitLegalMoves() {
        this.legalMoves.clear();
        int[] pos = stringToPos(this.position);

        if (this.name.contains(this.board.currentPlayer)) {

            //Initialising the legal moves of pawns

            if (this.name.contains("Pawn")) {
                if (this.name.contains("black")) {
                    legalMovesPawn(pos[0], pos[1] + 1);
                } else if (this.name.contains("white")) {
                    legalMovesPawn(pos[0], pos[1] - 1);
                }
            }

            //Initializing the moves of knights

            if (this.name.contains("Knight")) {

                legalMovesKnight(pos[0] + 1, pos[1] - 2);
                legalMovesKnight(pos[0] + 1, pos[1] + 2);
                legalMovesKnight(pos[0] - 1, pos[1] - 2);
                legalMovesKnight(pos[0] - 1, pos[1] + 2);
                legalMovesKnight(pos[0] - 2, pos[1] + 1);
                legalMovesKnight(pos[0] - 2, pos[1] - 1);
                legalMovesKnight(pos[0] + 2, pos[1] - 1);
                legalMovesKnight(pos[0] + 2, pos[1] + 1);
            } else if (this.name.contains("Bishop")) {

                legalMovesPieces(pos[0], pos[1], 1, 1);
                legalMovesPieces(pos[0], pos[1], -1, 1);
                legalMovesPieces(pos[0], pos[1], 1, -1);
                legalMovesPieces(pos[0], pos[1], -1, -1);
            } else if (this.name.contains("Rook")) {

                legalMovesPieces(pos[0], pos[1], 1, 0);
                legalMovesPieces(pos[0], pos[1], -1, 0);
                legalMovesPieces(pos[0], pos[1], 0, 1);
                legalMovesPieces(pos[0], pos[1], 0, -1);
            } else if (this.name.contains("Queen")) {
                //Diagonal
                legalMovesPieces(pos[0], pos[1], 1, 1);
                legalMovesPieces(pos[0], pos[1], -1, 1);
                legalMovesPieces(pos[0], pos[1], 1, -1);
                legalMovesPieces(pos[0], pos[1], -1, -1);

                //Horizontal
                legalMovesPieces(pos[0], pos[1], 1, 0);
                legalMovesPieces(pos[0], pos[1], -1, 0);
                legalMovesPieces(pos[0], pos[1], 0, 1);
                legalMovesPieces(pos[0], pos[1], 0, -1);
            } else if (this.name.contains("King")) {
                //Diagonal
                legalMovesKing(pos[0] + 1, pos[1] + 1);
                legalMovesKing(pos[0] + 1, pos[1] - 1);
                legalMovesKing(pos[0] - 1, pos[1] + 1);
                legalMovesKing(pos[0] - 1, pos[1] - 1);

                //Horizontal
                legalMovesKing(pos[0] + 1, pos[1]);
                legalMovesKing(pos[0] - 1, pos[1]);
                legalMovesKing(pos[0], pos[1] + 1);
                legalMovesKing(pos[0], pos[1] - 1);
            }
        }
    }

    public void innitLegalAllMoves() {
        this.legalMoves.clear();
        int[] pos = stringToPos(this.position);


        //Initialising the legal moves of pawns

        if (this.name.contains("Pawn")) {
            if (this.name.contains("black")) {
                legalMovesPawn(pos[0], pos[1] + 1);
            } else if (this.name.contains("white")) {
                legalMovesPawn(pos[0], pos[1] - 1);
            }
        }

        //Initializing the moves of knights

        if (this.name.contains("Knight")) {

            legalMovesKnight(pos[0] + 1, pos[1] - 2);
            legalMovesKnight(pos[0] + 1, pos[1] + 2);
            legalMovesKnight(pos[0] - 1, pos[1] - 2);
            legalMovesKnight(pos[0] - 1, pos[1] + 2);
            legalMovesKnight(pos[0] - 2, pos[1] + 1);
            legalMovesKnight(pos[0] - 2, pos[1] - 1);
            legalMovesKnight(pos[0] + 2, pos[1] - 1);
            legalMovesKnight(pos[0] + 2, pos[1] + 1);
        } else if (this.name.contains("Bishop")) {

            legalMovesPieces(pos[0], pos[1], 1, 1);
            legalMovesPieces(pos[0], pos[1], -1, 1);
            legalMovesPieces(pos[0], pos[1], 1, -1);
            legalMovesPieces(pos[0], pos[1], -1, -1);
        } else if (this.name.contains("Rook")) {

            legalMovesPieces(pos[0], pos[1], 1, 0);
            legalMovesPieces(pos[0], pos[1], -1, 0);
            legalMovesPieces(pos[0], pos[1], 0, 1);
            legalMovesPieces(pos[0], pos[1], 0, -1);
        } else if (this.name.contains("Queen")) {
            //Diagonal
            legalMovesPieces(pos[0], pos[1], 1, 1);
            legalMovesPieces(pos[0], pos[1], -1, 1);
            legalMovesPieces(pos[0], pos[1], 1, -1);
            legalMovesPieces(pos[0], pos[1], -1, -1);

            //Horizontal
            legalMovesPieces(pos[0], pos[1], 1, 0);
            legalMovesPieces(pos[0], pos[1], -1, 0);
            legalMovesPieces(pos[0], pos[1], 0, 1);
            legalMovesPieces(pos[0], pos[1], 0, -1);
        } else if (this.name.contains("King")) {
            //Diagonal
            legalMovesKing(pos[0] + 1, pos[1] + 1);
            legalMovesKing(pos[0] + 1, pos[1] - 1);
            legalMovesKing(pos[0] - 1, pos[1] + 1);
            legalMovesKing(pos[0] - 1, pos[1] - 1);

            //Horizontal
            legalMovesKing(pos[0] + 1, pos[1]);
            legalMovesKing(pos[0] - 1, pos[1]);
            legalMovesKing(pos[0], pos[1] + 1);
            legalMovesKing(pos[0], pos[1] - 1);
        }

    }

    public void canSwap() {
        if (this.name.contains("King")) {
            innitControlled();
        }

        //When the tile is not empty
        if (!this.name.isEmpty() && !this.canBeCaptured) {
            resetBoard();
            innitLegalMoves();
            displayLegalMoves(this.legalMoves);
            this.board.chosen = this;
        }

        //When a piece can be Captured
        else if (this.canBeCaptured) {
            this.board.chosen.neverMoved = false;
            for (String move : this.board.chosen.legalMoves) {
                if (this.position.equals(move)) {
                    capture(this, this.board.chosen);
                    this.board.changeCurrentPlayer();
                }
            }
            reset();
            resetBoard();
        }

        //When the tile is empty
        else {
            this.board.chosen.neverMoved = false;
            if (this.board.chosen.name.contains("King") && this.board.chosen.canCastle() && isCastlePosition(this.position)) {
                castling(this.board.chosen, this.position);
                this.board.changeCurrentPlayer();
            } else {
                for (String move : this.board.chosen.legalMoves) {
                    if (this.position.equals(move)) {
                        swap(this.board.chosen, this);
                        this.board.changeCurrentPlayer();
                    }
                }
            }

            reset();
            resetBoard();
        }
    }

    public void swap(Pieces p1, Pieces p2) {
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

    public void capture(Pieces captured, Pieces capturing) {
        captured.name = capturing.name;
        capturing.name = "";

        captured.button.setGraphic(capturing.button.getGraphic());
        capturing.button.setGraphic(null);
        captured.canBeCaptured = false;
    }

    public void innitControlled() {

        for (int i = 0; i < this.board.gridGame.length; i++) {
            for (int j = 0; j < this.board.gridGame[0].length; j++) {
                if (!this.board.gridGame[i][j].name.isEmpty()) {
                    int[] pos = stringToPos(this.board.gridGame[i][j].position);
                    String name = this.board.gridGame[i][j].name;

                    if (this.board.gridGame[i][j].name.contains("Pawn")) {
                        defineControlledPawn(name, pos[0], pos[1]);
                    } else if (this.board.gridGame[i][j].name.contains("Knight")) {
                        defineControlled(name, pos[0] + 1, pos[1] - 2);
                        defineControlled(name, pos[0] + 1, pos[1] + 2);
                        defineControlled(name, pos[0] - 1, pos[1] - 2);
                        defineControlled(name, pos[0] - 1, pos[1] + 2);
                        defineControlled(name, pos[0] - 2, pos[1] + 1);
                        defineControlled(name, pos[0] - 2, pos[1] - 1);
                        defineControlled(name, pos[0] + 2, pos[1] - 1);
                        defineControlled(name, pos[0] + 2, pos[1] + 1);
                    } else if (this.board.gridGame[i][j].name.contains("Bishop")) {
                        defineControlled(name, pos[0], pos[1], 1, 1);
                        defineControlled(name, pos[0], pos[1], -1, 1);
                        defineControlled(name, pos[0], pos[1], 1, -1);
                        defineControlled(name, pos[0], pos[1], -1, -1);
                    } else if (this.board.gridGame[i][j].name.contains("Rook")) {

                        defineControlled(name, pos[0], pos[1], 1, 0);
                        defineControlled(name, pos[0], pos[1], -1, 0);
                        defineControlled(name, pos[0], pos[1], 0, 1);
                        defineControlled(name, pos[0], pos[1], 0, -1);
                    } else if (this.board.gridGame[i][j].name.contains("Queen")) {
                        //Diagonal
                        defineControlled(name, pos[0], pos[1], 1, 1);
                        defineControlled(name, pos[0], pos[1], -1, 1);
                        defineControlled(name, pos[0], pos[1], 1, -1);
                        defineControlled(name, pos[0], pos[1], -1, -1);

                        //Horizontal
                        defineControlled(name, pos[0], pos[1], 1, 0);
                        defineControlled(name, pos[0], pos[1], -1, 0);
                        defineControlled(name, pos[0], pos[1], 0, 1);
                        defineControlled(name, pos[0], pos[1], 0, -1);
                    } else if (this.board.gridGame[i][j].name.contains("King")) {
                        //Diagonal
                        defineControlled(name, pos[0] + 1, pos[1] + 1);
                        defineControlled(name, pos[0] + 1, pos[1] - 1);
                        defineControlled(name, pos[0] - 1, pos[1] + 1);
                        defineControlled(name, pos[0] - 1, pos[1] - 1);

                        //Horizontal
                        defineControlled(name, pos[0] + 1, pos[1]);
                        defineControlled(name, pos[0] - 1, pos[1]);
                        defineControlled(name, pos[0], pos[1] + 1);
                        defineControlled(name, pos[0], pos[1] - 1);
                    }
                }
            }
        }
    }

    public void defineControlledPawn(String name, int num1, int num2) {
        try {
            if (name.contains("black")) {
                this.board.gridGame[num1 + 1][num2 + 1].controlledByBlack = true;
                this.board.gridGame[num1 - 1][num2 + 1].controlledByBlack = true;
            }
            if (name.contains("white")) {
                this.board.gridGame[num1 + 1][num2 - 1].controlledByWhite = true;
                this.board.gridGame[num1 - 1][num2 - 1].controlledByWhite = true;
            }
        } catch (Exception ignored) {
        }
    }

    public void defineControlled(String name, int num1, int num2) {
        try {
            if (this.board.gridGame[num1][num2].name.isEmpty()) {
                if (name.contains("white")) {
                    this.board.gridGame[num1][num2].controlledByWhite = true;
                } else if (name.contains("black")) {
                    this.board.gridGame[num1][num2].controlledByBlack = true;
                }
            }

            if (this.board.gridGame[num1][num2].name.contains("black") && name.contains("white")) {
                this.board.gridGame[num1][num2].controlledByWhite = true;
            }
            if (this.board.gridGame[num1][num2].name.contains("white") && name.contains("black")) {
                this.board.gridGame[num1][num2].controlledByBlack = true;
            }
        } catch (Exception ignored) {
        }
    }

    public void defineControlled(String name, int num1, int num2, int column, int row) {
        int column1 = column;
        int row1 = row;

        try {
            for (int i = 0; i < 8; i++) {
                if (this.board.gridGame[num1 + column1][num2 + row1].name.contains("white") && name.contains("white") || this.board.gridGame[num1 + column1][num2 + row1].name.contains("black") && name.contains("black")) {
                    break;
                }
                if (this.board.gridGame[num1 + column1][num2 + row1].name.isEmpty() && name.contains("white")) {
                    this.board.gridGame[num1 + column1][num2 + row1].controlledByWhite = true;
                } else if (this.board.gridGame[num1 + column1][num2 + row1].name.isEmpty() && name.contains("black")) {
                    this.board.gridGame[num1 + column1][num2 + row1].controlledByBlack = true;
                } else if (this.board.gridGame[num1 + column1][num2 + row1].name.contains("white") && name.contains("black")) {
                    this.board.gridGame[num1 + column1][num2 + row1].controlledByBlack = true;
                    break;
                } else if (this.board.gridGame[num1 + column1][num2 + row1].name.contains("black") && name.contains("white")) {
                    this.board.gridGame[num1 + column1][num2 + row1].controlledByWhite = true;
                    break;
                }
                if (column1 > 0) column1++;
                else if (column1 < 0) column1--;

                if (row1 > 0) row1++;
                else if (row1 < 0) row1--;
            }
        } catch (Exception ignored) {
        }
    }

    public void reset() {
        for (int i = 0; i < this.board.gridGame.length; i++) {
            for (int j = 0; j < this.board.gridGame[0].length; j++) {
                this.board.gridGame[i][j].canBeCaptured = false;
                this.board.gridGame[i][j].controlledByBlack = false;
                this.board.gridGame[i][j].controlledByWhite = false;
            }
        }
    }

    public Node toNode() {
        return this.button;
    }

    public String posToString(int[] pos) {
        String position = "";
        switch (pos[0]) {
            case 0 -> position += "a";
            case 1 -> position += "b";
            case 2 -> position += "c";
            case 3 -> position += "d";
            case 4 -> position += "e";
            case 5 -> position += "f";
            case 6 -> position += "g";
            case 7 -> position += "h";
        }

        switch (pos[1]) {
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

    public int[] stringToPos(String position) {
        int[] pos = new int[2];

        switch (position.charAt(0)) {
            case 'a' -> pos[0] = 0;
            case 'b' -> pos[0] = 1;
            case 'c' -> pos[0] = 2;
            case 'd' -> pos[0] = 3;
            case 'e' -> pos[0] = 4;
            case 'f' -> pos[0] = 5;
            case 'g' -> pos[0] = 6;
            case 'h' -> pos[0] = 7;
        }

        switch (position.charAt(1)) {
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

    public void displayLegalMoves(ArrayList<String> array) {
        //rgb(178,34,34)
        Color red = new Color(0.698, 0.13333, 0.133333, 1);

        try{
            for (String arr : array) {
                if(!arr.contains("Protected")){
                    int[] pos = stringToPos(arr);
                    this.board.gridGame[pos[0]][pos[1]].button.setBackground(Background.fill(red));
                }
            }
        } catch (Exception ignored) {}

    }

    public void resetBoard() {
        Color white = new Color(0.97, 0.84, 0.68, 1);
        Color black = new Color(0.482, 0.396, 0.286, 1);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i % 2 == 1 && j % 2 == 1) this.board.gridGame[i][j].button.setBackground(Background.fill(white));
                else if (i % 2 == 0 && j % 2 == 0)
                    this.board.gridGame[i][j].button.setBackground(Background.fill(white));
                else this.board.gridGame[i][j].button.setBackground(Background.fill(black));
            }
        }
    }


    public void legalMovesPawn(int num1, int num2) {
        try {
            if (this.neverMoved) {
                if (this.name.contains("black")) {
                    if (this.board.gridGame[num1][num2 + 1].name.isEmpty() && this.board.gridGame[num1][num2].name.isEmpty()) {
                        this.legalMoves.add(this.board.gridGame[num1][num2 + 1].position);
                    }
                } else if (this.name.contains("white")) {
                    if (this.board.gridGame[num1][num2 - 1].name.isEmpty() && this.board.gridGame[num1][num2].name.isEmpty()) {
                        this.legalMoves.add(this.board.gridGame[num1][num2 - 1].position);
                    }
                }
            }
            if (this.board.gridGame[num1][num2].name.isEmpty()) {
                this.legalMoves.add(this.board.gridGame[num1][num2].position);
            }

            try {
                if (!this.board.gridGame[num1 + 1][num2].name.contains("King")) {
                    protectingPiece(num1 + 1, num2);

                    if (this.board.gridGame[num1 + 1][num2].name.contains("white") && this.name.contains("black") || this.board.gridGame[num1 + 1][num2].name.contains("black") && this.name.contains("white")) {
                        this.board.gridGame[num1 + 1][num2].canBeCaptured = true;
                        this.legalMoves.add(this.board.gridGame[num1 + 1][num2].position);
                    }
                }
            } catch (Exception ignored) {
            }

            if (!this.board.gridGame[num1 - 1][num2].name.contains("King")) {
                protectingPiece(num1 - 1, num2);

                if (this.board.gridGame[num1 - 1][num2].name.contains("white") && this.name.contains("black") || this.board.gridGame[num1 - 1][num2].name.contains("black") && this.name.contains("white")) {
                    this.board.gridGame[num1 - 1][num2].canBeCaptured = true;
                    this.legalMoves.add(this.board.gridGame[num1 - 1][num2].position);
                }
            }

        } catch (Exception ignored) {
        }
    }

    public void legalMovesKnight(int num1, int num2){
        try {
            if (this.board.gridGame[num1][num2].name.isEmpty()) {
                this.legalMoves.add(this.board.gridGame[num1][num2].position);
            }
            if (!this.board.gridGame[num1][num2].name.contains("King")) {

                //Protecting a piece When they are the same color by adding protected to the position to not display the move

                protectingPiece(num1, num2);

                //Normal Capture
                if (this.board.gridGame[num1][num2].name.contains("white") && this.name.contains("black")) {
                    this.board.gridGame[num1][num2].canBeCaptured = true;
                    this.legalMoves.add(this.board.gridGame[num1][num2].position);
                } else if (this.board.gridGame[num1][num2].name.contains("black") && this.name.contains("white")) {
                    this.board.gridGame[num1][num2].canBeCaptured = true;
                    this.legalMoves.add(this.board.gridGame[num1][num2].position);
                }
            }

        } catch (Exception ignored) {
        }
    }

    public void legalMovesKing(int num1, int num2) {
        try {
            //Je sais plus pourquoi j'ai fait ca
            if (!(this.name.equals("blackKing") && this.board.gridGame[num1][num2].controlledByWhite) && !(this.name.equals("whiteKing") && this.board.gridGame[num1][num2].controlledByBlack)) {

                //If the chosen tile is empty move like a king move in chess
                if (this.board.gridGame[num1][num2].name.isEmpty()) {
                    this.legalMoves.add(this.board.gridGame[num1][num2].position);
                }

                protectingPiece(num1, num2);

                //Capturing when being black maybe I will change it
                if(this.board.gridGame[num1][num2].name.contains("white") && this.name.contains("black")){
                    if (!(isProtected(this.board.gridGame[num1][num2]) && this.name.contains("King"))) {
                        this.board.gridGame[num1][num2].canBeCaptured = true;
                        this.legalMoves.add(this.board.gridGame[num1][num2].position);
                    }
                }

                //Capturing when being white maybe I will change it
                else if(this.board.gridGame[num1][num2].name.contains("black") && this.name.contains("white")){
                    if (!(isProtected(this.board.gridGame[num1][num2]) && this.name.contains("King"))) {
                        this.board.gridGame[num1][num2].canBeCaptured = true;
                        this.legalMoves.add(this.board.gridGame[num1][num2].position);
                    }
                }
                //castle
                castle();
            }
        } catch (Exception ignored) {
        }
    }

    public void legalMovesPieces(int num1, int num2, int column, int row) {
        int column1 = column;
        int row1 = row;

        //TODO Have to find a way to add encountering same color piece to the legalMoves list without displaying it nor being able to capture
        try {
            for (int i = 0; i < 8; i++) {
                if (this.board.gridGame[num1 + column1][num2 + row1].name.contains("white") && this.name.contains("white") || this.board.gridGame[num1 + column1][num2 + row1].name.contains("black") && this.name.contains("black")) {
                    this.legalMoves.add(this.board.gridGame[num1 + column1][num2 + row1].position + "Protected");
                    break;
                }

                else if (this.board.gridGame[num1 + column1][num2 + row1].name.isEmpty()) {
                    this.legalMoves.add(this.board.gridGame[num1 + column1][num2 + row1].position);
                }

                if (!this.board.gridGame[num1 + column1][num2 + row1].name.contains("King")) {
                    if (this.board.gridGame[num1 + column1][num2 + row1].name.contains("white") && this.name.contains("black")) {
                        this.board.gridGame[num1 + column1][num2 + row1].canBeCaptured = true;
                        this.legalMoves.add(this.board.gridGame[num1 + column1][num2 + row1].position);
                        break;
                    } else if (this.board.gridGame[num1 + column1][num2 + row1].name.contains("black") && this.name.contains("white")) {
                        this.board.gridGame[num1 + column1][num2 + row1].canBeCaptured = true;
                        this.legalMoves.add(this.board.gridGame[num1 + column1][num2 + row1].position);
                        break;
                    }
                }

                if (column1 > 0) column1++;
                else if (column1 < 0) column1--;

                if (row1 > 0) row1++;
                else if (row1 < 0) row1--;
            }
        } catch (Exception ignored) {
        }
    }

    public boolean isProtected(Pieces piece) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(!this.board.gridGame[i][j].name.contains("King")){
                    this.board.gridGame[i][j].innitLegalAllMoves();
                }
                for (String arr : this.board.gridGame[i][j].legalMoves) {
                    if (arr.equals(piece.position + "Protected") && ((piece.name.contains("black") && this.board.gridGame[i][j].name.contains("black")) || (piece.name.contains("white") && this.board.gridGame[i][j].name.contains("white")))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void protectingPiece(int a, int b){
        if (this.board.gridGame[a][b].name.contains("white") && this.name.contains("white") || this.board.gridGame[a][b].name.contains("black") && this.name.contains("black")) {
            this.legalMoves.add(this.board.gridGame[a][b].position + "Protected");
        }
    }

    public void castle() {
        if (this.name.equals("whiteKing")) {
            if (this.board.gridGame[4][7].neverMoved && this.board.gridGame[5][7].name.isEmpty() && !this.board.gridGame[5][7].controlledByBlack && this.board.gridGame[6][7].name.isEmpty() && !this.board.gridGame[6][7].controlledByBlack && this.board.gridGame[7][7].neverMoved) {
                this.legalMoves.add("g1+");
            }

            if (this.board.gridGame[4][7].neverMoved && this.board.gridGame[3][7].name.isEmpty() && !this.board.gridGame[3][7].controlledByBlack && this.board.gridGame[2][7].name.isEmpty() && !this.board.gridGame[2][7].controlledByBlack && this.board.gridGame[0][7].neverMoved) {
                this.legalMoves.add("c1+");
            }
        } else if (this.name.equals("blackKing")) {
            if (this.board.gridGame[4][0].neverMoved && this.board.gridGame[5][0].name.isEmpty() && !this.board.gridGame[5][0].controlledByWhite && this.board.gridGame[6][0].name.isEmpty() && !this.board.gridGame[6][0].controlledByWhite && this.board.gridGame[7][0].neverMoved) {
                this.legalMoves.add("g8+");
            }

            if (this.board.gridGame[4][0].neverMoved && this.board.gridGame[3][0].name.isEmpty() && !this.board.gridGame[3][0].controlledByWhite && this.board.gridGame[2][0].name.isEmpty() && !this.board.gridGame[2][0].controlledByWhite && this.board.gridGame[0][0].neverMoved) {
                this.legalMoves.add("c8+");
            }
        }
    }

    public boolean canCastle() {
        for (String move : this.legalMoves) {
            if (move.equals("g1+") || move.equals("c1+") || move.equals("g8+") || move.equals("c8+")) {
                return true;
            }
        }
        return false;
    }

    public void castling(Pieces piece, String position) {
        if (piece.name.equals("whiteKing")) {
            this.board.gridGame[4][7].neverMoved = false;
            if (position.equals("g1")) {
                this.board.gridGame[7][7].neverMoved = false;
                swap(this.board.gridGame[4][7], this.board.gridGame[6][7]);
                swap(this.board.gridGame[7][7], this.board.gridGame[5][7]);
            } else if (position.equals("c1")) {
                this.board.gridGame[0][7].neverMoved = false;
                swap(this.board.gridGame[4][7], this.board.gridGame[2][7]);
                swap(this.board.gridGame[0][7], this.board.gridGame[3][7]);
            }
        } else if (piece.name.equals("blackKing")) {
            this.board.gridGame[4][0].neverMoved = false;
            if (position.equals("g8")) {
                this.board.gridGame[7][0].neverMoved = false;
                swap(this.board.gridGame[4][0], this.board.gridGame[6][0]);
                swap(this.board.gridGame[7][0], this.board.gridGame[5][0]);
            } else if (position.equals("c8")) {
                this.board.gridGame[0][0].neverMoved = false;
                swap(this.board.gridGame[4][0], this.board.gridGame[2][0]);
                swap(this.board.gridGame[0][0], this.board.gridGame[3][0]);
            }
        }
    }

    public boolean isCastlePosition(String pos) {
        return pos.equals("g1") || pos.equals("c1") || pos.equals("g8") || pos.equals("c8");
    }


}

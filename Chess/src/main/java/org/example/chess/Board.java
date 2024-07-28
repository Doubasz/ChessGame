package org.example.chess;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Board {
    Pieces[][] gridGame = new Pieces[8][8];
    GridPane grid = new GridPane();
    Pieces chosen;
    String currentPlayer = "white";


    public Board(){

        grid.setLayoutX(45);
        grid.setLayoutY(45);
        Color white = new Color(0.97, 0.84, 0.68, 1);
        Color black = new Color(0.482, 0.396, 0.286, 1);

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                Button button = new Button();
                button.setPrefWidth(50);
                button.setPrefHeight(50);

                if(i % 2 == 1 && j % 2 == 1) button.setBackground(Background.fill(white));
                else if(i % 2 == 0 && j % 2 == 0) button.setBackground(Background.fill(white));
                else button.setBackground(Background.fill(black));

                gridGame[i][j] = new Pieces(this, button, i, j, false);
                grid.add(gridGame[i][j].toNode(), i, j);
            }
        }
    }

    public Parent toParent(){
        return this.grid;
    }

    public void changeCurrentPlayer(){
        if(this.currentPlayer.equals("white")) this.currentPlayer = "black";
        else this.currentPlayer = "white";
    }


}

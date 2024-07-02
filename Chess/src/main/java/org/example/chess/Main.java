package org.example.chess;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    GridPane grid = new GridPane();
    Pieces[][] gridGame = new Pieces[8][8];


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("boardScene.fxml"));

        Image icon = new Image("blackPawn.png");

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

                gridGame[i][j] = new Pieces(button, i, j);
                grid.add(gridGame[i][j].toNode(), i, j);
            }
        }

        innitPieces();
        makingMoves();

        Scene scene = new Scene(grid, 500, 500);
        stage.setTitle("Chess");
        stage.setScene(scene);
        stage.getIcons().add(icon);
        stage.show();
    }

    public void makingMoves(){
        Button temp = new Button();
        temp.setGraphic(gridGame[4][1].getGraphic());
        gridGame[4][1].setGraphic(gridGame[4][3].getGraphic());
        gridGame[4][3].setGraphic(temp.getGraphic());
    }

    public void innitPieces() throws NullPointerException{

        //Rooks
        ImageView blackRook = new ImageView(getClass().getResource("img/blackRook.png").toExternalForm());
        ImageView blackRook1 = new ImageView(getClass().getResource("img/blackRook.png").toExternalForm());

        gridGame[0][0].setGraphic(blackRook);
        gridGame[7][0].setGraphic(blackRook1);

        //Bishops
        ImageView blackBishop = new ImageView(getClass().getResource("img/blackBishop.png").toExternalForm());
        ImageView blackBishop1 = new ImageView(getClass().getResource("img/blackBishop.png").toExternalForm());

        gridGame[2][0].setGraphic(blackBishop);
        gridGame[5][0].setGraphic(blackBishop1);

        //Knights
        ImageView blackKnight = new ImageView(getClass().getResource("img/blackKnight.png").toExternalForm());
        ImageView blackKnight1 = new ImageView(getClass().getResource("img/blackKnight.png").toExternalForm());

        gridGame[1][0].setGraphic(blackKnight);
        gridGame[6][0].setGraphic(blackKnight1);

        //Queens
        ImageView blackQueen = new ImageView(getClass().getResource("img/blackQueen.png").toExternalForm());

        gridGame[3][0].setGraphic(blackQueen);

        //Kings
        ImageView blackKing = new ImageView(getClass().getResource("img/blackKing.png").toExternalForm());

        gridGame[4][0].setGraphic(blackKing);

        //Pawns
        ImageView blackPawn = new ImageView(getClass().getResource("img/blackPawn.png").toExternalForm());
        ImageView blackPawn1 = new ImageView(getClass().getResource("img/blackPawn.png").toExternalForm());
        ImageView blackPawn2 = new ImageView(getClass().getResource("img/blackPawn.png").toExternalForm());
        ImageView blackPawn3 = new ImageView(getClass().getResource("img/blackPawn.png").toExternalForm());
        ImageView blackPawn4 = new ImageView(getClass().getResource("img/blackPawn.png").toExternalForm());
        ImageView blackPawn5 = new ImageView(getClass().getResource("img/blackPawn.png").toExternalForm());
        ImageView blackPawn6 = new ImageView(getClass().getResource("img/blackPawn.png").toExternalForm());
        ImageView blackPawn7 = new ImageView(getClass().getResource("img/blackPawn.png").toExternalForm());

        gridGame[0][1].setGraphic(blackPawn);
        gridGame[1][1].setGraphic(blackPawn1);
        gridGame[2][1].setGraphic(blackPawn2);
        gridGame[3][1].setGraphic(blackPawn3);
        gridGame[4][1].setGraphic(blackPawn4);
        gridGame[5][1].setGraphic(blackPawn5);
        gridGame[6][1].setGraphic(blackPawn6);
        gridGame[7][1].setGraphic(blackPawn7);
    }

    public static void main(String[] args) {
        launch();
    }
}
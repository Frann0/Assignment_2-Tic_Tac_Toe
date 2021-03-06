/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import tictactoe.bll.GameBoardFactory;
import tictactoe.bll.IGameModel;
import tictactoe.gui.model.ScoreModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Stegger
 */

public class TicTacViewController implements Initializable {
    @FXML
    private ChoiceBox<GameBoardFactory.GAME_MODE> choicePlayMode;

    @FXML
    private ListView<String> lstScores;

    @FXML
    private Label lblPlayer;

    @FXML
    private Button btnNewGame;

    @FXML
    private GridPane gridPane;

    /**
     * The prefix text that is shown before the actual player who's turn it is.
     */
    private static final String TXT_PLAYER = "Player: ";

    private GameBoardFactory.GAME_MODE currentGameMode;
    private IGameModel game;
    private ScoreModel scoreModel;
    private List nodes;

    /**
     * Initialize method is called at construction time AFTER the constructor is called, and after all GUI controls are created.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        scoreModel = new ScoreModel();
        nodes = new ArrayList<Node>();
        nodes.addAll(gridPane.getChildren());
        lstScores.setItems(scoreModel.getWinners());
        choicePlayMode.getItems().addAll(GameBoardFactory.GAME_MODE.values());
        choicePlayMode.getSelectionModel().selectLast();
        currentGameMode = choicePlayMode.getSelectionModel().getSelectedItem();

        game = GameBoardFactory.getGameModel(currentGameMode);
        game.resetBoard();
        setPlayer();
    }

    /**
     * Event handler that is called whenever a Player clicks a button in the game field.
     *
     * @param event The Button click event
     */
    @FXML
    private void handleButtonAction(ActionEvent event) {
        try {
            Integer row = GridPane.getRowIndex((Node) event.getSource());
            Integer col = GridPane.getColumnIndex((Node) event.getSource());

            int r = (row == null) ? 0 : row;
            int c = (col == null) ? 0 : col;

            if (game.play(c, r)) {
                int player = game.getNextPlayer();
                game.incrementPlayer();
                Button btn = (Button) event.getSource();
                String XrO = player == 0 ? "X" : "O";
                btn.setText(XrO);
                game.setGrid(c,r,XrO,nodes);

                if (game.isGameOver()) {
                    int winner = game.getWinner();
                    displayWinner(winner);
                    if(winner == 0){
                        scoreModel.setNextWinner("X");
                    } else if (winner == 1) {
                        scoreModel.setNextWinner("O");
                    } else{
                        scoreModel.setNextWinner("Draw :(");
                    }
                    lstScores.setItems(scoreModel.getWinners());
                } else {
                    setPlayer();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Eventhandler that is called whenever the players want's to start a new game.
     * The method will switch game mode if the player has chosen so.
     *
     * @param event
     */
    @FXML
    private void handleNewGame(ActionEvent event) {
        if (currentGameMode == choicePlayMode.getSelectionModel().getSelectedItem()) {
            game.resetBoard();
        } else {
            currentGameMode = choicePlayMode.getSelectionModel().getSelectedItem();
            game = GameBoardFactory.getGameModel(currentGameMode);
        }
        game.resetBoard();
        setPlayer();
        clearBoard();
    }

    /**
     * Updates the label displaying who's turn it is.
     */
    private void setPlayer() {
        lblPlayer.setText(TXT_PLAYER + game.getNextPlayer());
    }

    /**
     * Updates the label to display the winner of the game.
     *
     * @param winner
     */
    private void displayWinner(int winner) {
        String message = switch (winner) {
            case -1 -> "It's a draw :-(";
            case 0 -> "Player 0 Wins";
            case 1 -> "Player 1 Wins";
            default -> "Player " + winner + " wins!!!";
        };
        lblPlayer.setText(message);
    }

    /**
     * Clears the board so that all Buttons of the board do NOT display anything.
     */
    private void clearBoard() {
        for (Node n : gridPane.getChildren()) {
            Button btn = (Button) n;
            btn.setText("");
        }
    }
}

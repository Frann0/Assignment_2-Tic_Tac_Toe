package tictactoe.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ScoreModel {

    private ObservableList<String> winners;

    public ScoreModel() {
        winners = FXCollections.observableArrayList();
    }

    public ObservableList<String> getWinners() {
        return winners;
    }

    public void setNextWinner(String winner) {
        winners.add(0,winner);
        if (winners.size() > 16){
            winners.remove(winners.size()-1);
        }
    }

}
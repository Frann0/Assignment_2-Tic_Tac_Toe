package tictactoe.bll;

import javafx.scene.layout.GridPane;

public class GameBoardFactory {

    public static enum GAME_MODE {
        SINGLE_PLAYER, TWO_PLAYERS;

        @Override
        public String toString() {
            switch (this) {
                case TWO_PLAYERS:
                    return "Two players";

                case SINGLE_PLAYER:
                    return "Single player";

                default:
                    throw new IllegalStateException("Unexpected value: " + this);
            }
        }
    }

    public static IGameModel getGameModel(GAME_MODE gameMode) {
        switch (gameMode) {
            case SINGLE_PLAYER:
                return new GameBoardSinglePlayer();
            case TWO_PLAYERS:
                return new GameBoardTwoPlayer();
            default:
                throw new IllegalStateException("Unexpected value: " + gameMode);
        }
    }

}

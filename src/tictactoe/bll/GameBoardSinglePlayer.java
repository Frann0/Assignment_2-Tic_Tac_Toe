package tictactoe.bll;

import javafx.scene.control.Button;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The GameBoardSinglePlayer class is the optional and advanced implementation for the TicTacToe assignment.
 * It is used for games where there are one human player vs. a computer player.
 */
public class GameBoardSinglePlayer implements IGameModel {
    private int tur;
    private int tureTilbage;
    private final String[][] grid;

    protected GameBoardSinglePlayer() {
        tur = 0;
        tureTilbage = 9;
        grid = new String[3][3];
    }

    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    @Override
    public int getNextPlayer() {
        return tur % 2;
    }

    public void incrementPlayer() {
        if (tur == 0) {
            tur++;
        } else {
            tur = 0;
        }
    }

    /**
     * Attempts to let the current player play at the given coordinates. It the
     * attempt is successful the current player has ended his turn and it is the
     * next players turn.
     *
     * @param col column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false. If gameOver ==
     * true this method will always return false.
     */
    @Override
    public boolean play(int col, int row) {
        return (!isGameOver() && grid[col][row].matches(""));
    }

    /**
     * Tells us if the game has ended either by draw or by meeting the winning
     * condition.
     *
     * @return true if the game is over, else it will retun false.
     */
    @Override
    public boolean isGameOver() {
        if (tureTilbage > 0) {
            if (checkXWin() || checkOWin()) {
                return true;
            }
            return false;
        }
        return true;
    }
    //[0,0 0,1 0,2]
    //[1,0 1,1 1,2]
    //[2,0 2,1 2,2]

    public boolean checkXWin() {
        return ( //Vandret
                grid[0][0].matches("[X]") && grid[1][0].matches("[X]") && grid[2][0].matches("[X]") ||
                        grid[0][1].matches("[X]") && grid[1][1].matches("[X]") && grid[2][1].matches("[X]") ||
                        grid[0][2].matches("[X]") && grid[1][2].matches("[X]") && grid[2][2].matches("[X]") ||
                        //Lodret
                        grid[0][0].matches("[X]") && grid[0][1].matches("[X]") && grid[0][2].matches("[X]") ||
                        grid[1][0].matches("[X]") && grid[1][1].matches("[X]") && grid[1][2].matches("[X]") ||
                        grid[2][0].matches("[X]") && grid[2][1].matches("[X]") && grid[2][2].matches("[X]") ||
                        //kryds
                        grid[0][0].matches("[X]") && grid[1][1].matches("[X]") && grid[2][2].matches("[X]") ||
                        grid[0][2].matches("[X]") && grid[1][1].matches("[X]") && grid[2][0].matches("[X]"));
    }

    public boolean checkOWin() {
        return ( //Vandret
                grid[0][0].matches("[O]") && grid[1][0].matches("[O]") && grid[2][0].matches("[O]") ||
                        grid[0][1].matches("[O]") && grid[1][1].matches("[O]") && grid[2][1].matches("[O]") ||
                        grid[0][2].matches("[O]") && grid[1][2].matches("[O]") && grid[2][2].matches("[O]") ||
                        //Lodret
                        grid[0][0].matches("[O]") && grid[0][1].matches("[O]") && grid[0][2].matches("[O]") ||
                        grid[1][0].matches("[O]") && grid[1][1].matches("[O]") && grid[1][2].matches("[O]") ||
                        grid[2][0].matches("[O]") && grid[2][1].matches("[O]") && grid[2][2].matches("[O]") ||
                        //kryds
                        grid[0][0].matches("[O]") && grid[1][1].matches("[O]") && grid[2][2].matches("[O]") ||
                        grid[0][2].matches("[O]") && grid[1][1].matches("[O]") && grid[2][0].matches("[O]"));
    }

    /**
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
     */
    @Override
    public int getWinner() {
        if (checkOWin()) {
            return 1;
        } else if (checkXWin()) {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * Resets the game to a new game state.
     */
    @Override
    public void resetBoard() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[i][j] = "";
            }
        }
        tureTilbage = 9;
        tur = 0;
    }

    @Override
    public void setGrid(int col, int row, String tekst, List nodes) {
        tureTilbage--;
        System.out.println(tureTilbage);
        grid[col][row] = tekst;
        computerMove(nodes);
    }

    private void computerMove(List nodes) {
        if (!isGameOver()) {
            int col = ThreadLocalRandom.current().nextInt(0, 3);
            int row = ThreadLocalRandom.current().nextInt(0, 3);
            if (play(col, row)) {
                grid[col][row] = "O";
                setAIButtonText(col,row,nodes);
                tureTilbage--;
                incrementPlayer();
            } else {
                computerMove(nodes);
            }
        }
    }

    private void setAIButtonText(int col, int row, List nodes) {
        int index = col + 3 * row;
        Button test = (Button) nodes.get(index);
        test.setText("O");
    }


}

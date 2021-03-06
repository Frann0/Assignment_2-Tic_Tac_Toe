package tictactoe.bll;

import java.util.List;

/**
 * The GameBoardTwoPlayer class is the mandatory implementation for the TicTacToe assignment.
 * It is used for games where there are two human players.
 */
public class GameBoardTwoPlayer implements IGameModel {
    private int tur;
    private int tureTilbage;
    private final String[][] grid;

    protected GameBoardTwoPlayer() {
        tur = 0;
        tureTilbage = 9;
        grid = new String[3][3];
        resetBoard();
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

    /**
     * Inkrementere vores spiller.
     */
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
     * @return true if the game is over, else it will return false.
     */
    @Override
    public boolean isGameOver() {
        if (tureTilbage > 0){
            return checkXWin() || checkOWin();
        }
        return true;
    }

    /**
     * Checker om X har vundet. Kan helt sikkert gøres på en bedre måde.
     * @return  Returnere hvor vidt X har vundet eller ej.
     */
    public boolean checkXWin() {
        //Vandret
        return (grid[0][0].matches("[X]") && grid[1][0].matches("[X]") && grid[2][0].matches("[X]") ||
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

    /**
     * Checker om O har vundet. Kan helt sikkert gøres på en bedre måde.
     * @return  Returnere hvor vidt O har vundet eller ej.
     */
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
        if (checkOWin()){
            return 1;
        }else if (checkXWin()){
            return 0;
        }else {
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

    /**
     * Funktion der sætter et given index i vores grid array til X eller O, I dette tilfælde begge, da computermove
     * Kaldes, fordi det er singleplayer. Og som sagt kaldes computerMove her, da så snart vi laver et træk, så skal
     * computeren også.
     * @param col   Collumn i grid arrayet
     * @param row   Row i grid arrayet
     * @param tekst Teksten der skal sættes
     * @param nodes nodes. bliver primært brugt i singlerplayer moden, for at finde computerens valg af knap.
     */
    @Override
    public void setGrid(int col, int row, String tekst,List nodes) {
        tureTilbage--;
        grid[col][row] = tekst;
    }

}
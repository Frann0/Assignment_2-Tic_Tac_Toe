package tictactoe.bll;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This is a test Class. You can use it to validate that your GameBoardTwoPlayer class works as intended.
 * DO NOT modify the code in this class.
 */
class GameBoardTwoPlayerTest {
    public List TOMLISTE = new ArrayList();

    /**
     * Test of getNextPlayer method, of class GameBoard.
     */
    @Test
    public void testGetNextPlayer()
    {
        IGameModel instance = new GameBoardTwoPlayer();
        int expResult = 0;

        int result = instance.getNextPlayer();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNextPlayer method, of class GameBoard.
     */
    @Test
    public void testGetNextPlayerAfterOneRound()
    {
        IGameModel instance = new GameBoardTwoPlayer();
        int expResult = 1;

        instance.play(0, 0);
        instance.setGrid(0,0,"X",TOMLISTE);
        instance.incrementPlayer(); // Vi incrementer egentligt ikke i hverken setGrid eller play. Der har vi vores egen funktion.
        int result = instance.getNextPlayer();

        assertEquals(expResult, result);
    }

    /**
     * Test of play method, of class GameBoard.
     */
    @Test
    public void testPlayAtZeroZero()
    {
        IGameModel instance = new GameBoardTwoPlayer();
        int col = 0;
        int row = 0;
        boolean expResult = true;

        boolean result = instance.play(col, row);
        instance.setGrid(0,0,"X",TOMLISTE);

        assertEquals(expResult, result);
    }

    /**
     * Test of play method, of class GameBoard.
     */
    @Test
    public void testPlayAtOneOne()
    {
        IGameModel instance = new GameBoardTwoPlayer();
        int col = 1;
        int row = 1;
        boolean expResult = true;

        boolean result = instance.play(col, row);

        assertEquals(expResult, result);
    }

    /**
     * Test of play method, of class GameBoard.
     */
    @Test
    public void testPlayAtTakenSpot()
    {
        IGameModel instance = new GameBoardTwoPlayer();
        int col = 1;
        int row = 1;
        boolean expResult = false;

        instance.play(col, row); //Play once
        instance.setGrid(col,row,"x", TOMLISTE);
        boolean result = instance.play(col, row); //Play same spot

        assertEquals(expResult, result);
    }

    /**
     * Test of isGameOver method, of class GameBoard.
     */
    @Test
    public void testIsGameOver()
    {
        IGameModel instance = new GameBoardTwoPlayer();

        instance.play(0, 0); //Player 0
        instance.setGrid(0,0,"X",TOMLISTE);
        instance.play(1, 0); //Player 1
        instance.setGrid(1,0,"O",TOMLISTE);
        instance.play(0, 1); //Player 0
        instance.setGrid(0,1,"X",TOMLISTE);
        instance.play(2, 0); //Player 1
        instance.setGrid(2,0,"O",TOMLISTE);
        instance.play(0, 2); //Player 0
        instance.setGrid(0,2,"X",TOMLISTE);

        boolean expResult = true;
        boolean result = instance.isGameOver();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWinner method, of class GameBoard.
     */
    @Test
    public void testGetWinnerPlayerZeroVeritcal()
    {
        IGameModel instance = new GameBoardTwoPlayer();
        int expResult = 0;

        instance.play(0, 0); //Player 0
        instance.setGrid(0,0,"X",TOMLISTE);
        instance.play(1, 0); //Player 1
        instance.setGrid(1,0,"O",TOMLISTE);
        instance.play(0, 1); //Player 0
        instance.setGrid(0,1,"X",TOMLISTE);
        instance.play(2, 0); //Player 1
        instance.setGrid(2,0,"O",TOMLISTE);
        instance.play(0, 2); //Player 0
        instance.setGrid(0,2,"X",TOMLISTE);

        int result = instance.getWinner();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWinner method, of class GameBoard.
     */
    @Test
    public void testGetWinnerPlayerOneHorizontal()
    {
        IGameModel instance = new GameBoardTwoPlayer();
        int expResult = 1;

        instance.play(1, 1); //Player 0
        instance.setGrid(1,1,"X",TOMLISTE);
        instance.play(0, 0); //Player 1
        instance.setGrid(0,0,"O",TOMLISTE);
        instance.play(0, 1); //Player 0
        instance.setGrid(0,1,"X",TOMLISTE);
        instance.play(2, 0); //Player 1
        instance.setGrid(2,0,"O",TOMLISTE);
        instance.play(1, 2); //Player 0
        instance.setGrid(1,2,"X",TOMLISTE);
        instance.play(1, 0); //Player 1
        instance.setGrid(1,0,"O",TOMLISTE);

        boolean isGameOver = instance.isGameOver();
        int result = instance.getWinner();
        assertTrue(isGameOver);
        assertEquals(expResult, result);
    }

    /**
     * Test of getWinner method, of class GameBoard.
     */
    @Test
    public void testGetWinnerDiagonally()
    {
        IGameModel instance = new GameBoardTwoPlayer();
        boolean expResult = true;

        instance.play(0, 0); //Player 0
        instance.setGrid(0,0,"X",TOMLISTE);
        instance.play(1, 0); //Player 1
        instance.setGrid(1,0,"O",TOMLISTE);
        instance.play(1, 1); //Player 0
        instance.setGrid(1,1,"X",TOMLISTE);
        instance.play(2, 0); //Player 1
        instance.setGrid(2,0,"O",TOMLISTE);
        instance.play(2, 2); //Player 0
        instance.setGrid(2,2,"X",TOMLISTE);

        boolean result = instance.isGameOver();
        assertEquals(expResult, result);
    }
}
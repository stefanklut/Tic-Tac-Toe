package com.example.stefan.tic_tac_toe;

public class Game {
    // Board specifications
    final private int BOARD_SIZE = 3;
    private TileState[][] board;

    // variables
    private Boolean playerOneTurn;  // true if player 1's turn, false if player 2's turn
    private int movesPlayed;
    private Boolean gameOver;

    public Game() {
        board = new TileState[BOARD_SIZE][BOARD_SIZE];
        for(int i=0; i<BOARD_SIZE; i++)
            for(int j=0; j<BOARD_SIZE; j++)
                board[i][j] = TileState.BLANK;

        playerOneTurn = true;
        gameOver = false;
    }

    public TileState choose(int row, int column) {
        TileState tile = board[row][column];
        if (tile == TileState.BLANK) {
            TileState newTile;
            if (playerOneTurn) {
                newTile = TileState.CROSS;
            } else {
                newTile = TileState.CIRCLE;
            }
            playerOneTurn = !playerOneTurn;
            board[row][column] = newTile;
            return newTile;
        } else {
            return TileState.INVALID;
        }
    }
}

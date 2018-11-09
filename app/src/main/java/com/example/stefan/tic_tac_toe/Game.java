package com.example.stefan.tic_tac_toe;

import java.io.Serializable;

// Game class
public class Game implements Serializable {
    // Board specifications
    final public int BOARD_SIZE = 3;
    private TileState[][] board;

    // variables
    private boolean playerOneTurn;  // true if player 1's turn, false if player 2's turn
    private int movesPlayed;
    public boolean gameOver;

    // Constructor for Game that fills the board with BLANK
    public Game() {
        board = new TileState[BOARD_SIZE][BOARD_SIZE];
        for(int i=0; i<BOARD_SIZE; i++)
            for(int j=0; j<BOARD_SIZE; j++)
                board[i][j] = TileState.BLANK;

        playerOneTurn = true;
        gameOver = false;
    }

    // Returns the board
    public TileState[][] getBoard() {
        return board;
    }

    // Select a tile and add the cross or circle depending on whose turn it is
    public TileState choose(int row, int column) {
        TileState tile = board[row][column];

        // If the tile that was chosen is blank change the tile to cross for player one and a cross
        // for player two and return the tile state. If the tile was not blank return invalid
        if (tile == TileState.BLANK) {
            movesPlayed++;
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

    // Check if a win condition was met
    public GameState won() {
        TileState checkTile;
        GameState winner;
        gameOver = true;

        // Whose turn was it last?
        if (!playerOneTurn) {
            checkTile = TileState.CROSS;
            winner = GameState.PLAYER_ONE;
        } else {
            checkTile = TileState.CIRCLE;
            winner = GameState.PLAYER_TWO;
        }
        // Check if there is a winner after the turn
        boolean diaWon = true;
        boolean antiDiaWon = true;

        for(int i=0; i<BOARD_SIZE; i++) {
            boolean rowWon = true;
            boolean colWon = true;
            // Check for winner in diagonal or anti-diagonal
            if (board[i][i] != checkTile) {
                diaWon = false;
            }
            if (board[i][(BOARD_SIZE-1)-i] != checkTile) {
                antiDiaWon = false;
            }
            if ((i == BOARD_SIZE - 1) && (diaWon || antiDiaWon)) {
                return winner;
            }

            for (int j=0; j<BOARD_SIZE; j++) {
                // Check for winner in row or column
                if (board[i][j] != checkTile) {
                    rowWon = false;
                }
                if (board[j][i] != checkTile) {
                    colWon = false;
                }
                if ((j == BOARD_SIZE - 1) && (rowWon || colWon)) {
                    return winner;
                }
            }
        }
        // Check for Draw, otherwise the game is still in progress
        if (movesPlayed == BOARD_SIZE*BOARD_SIZE) {
            return GameState.DRAW;
        } else {
            gameOver = false;
            return GameState.IN_PROGRESS;
        }
    }
}

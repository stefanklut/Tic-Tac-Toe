package com.example.stefan.tic_tac_toe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // variables
    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            game = (Game) savedInstanceState.getSerializable("gameClass");
            TileState[][] board = game.getBoard();
            for (int i = 0; i<game.BOARD_SIZE; i++) {
                for (int j = 0; j<game.BOARD_SIZE; j++) {
                    if (board[i][j] == TileState.CROSS){
                        int buttonId = getResources().getIdentifier(
                                "buttonR"+i+"C"+j,
                                "id",
                                getPackageName());
                        ((Button) findViewById(buttonId)).setText("X");
                    }
                    if (board[i][j] == TileState.CIRCLE){
                        int buttonId = getResources().getIdentifier(
                                "buttonR"+i+"C"+j,
                                "id",
                                getPackageName());
                        ((Button) findViewById(buttonId)).setText("O");
                    }
                }
            }
            CharSequence message = savedInstanceState.getCharSequence("message");
            ((TextView) findViewById(R.id.textViewMessage)).setText(message);
        } else {
            game = new Game();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("gameClass", game);
        outState.putCharSequence("message", ((TextView) findViewById(R.id.textViewMessage)).getText());
    }

    public void tileClicked(View view) {
        // Ignore button input if the game is over
        if (game.gameOver) {
            return;
        }

        int id = view.getId();
        int column = -1;
        int row = -1;
        TextView message = (TextView) findViewById(R.id.textViewMessage);

        // Loop over the board to check what button was pressed
        for (int i=0;i<game.BOARD_SIZE;i++) {
            for (int j=0;j<game.BOARD_SIZE;j++){
                int buttonId = getResources().getIdentifier(
                        "buttonR"+i+"C"+j,
                        "id",
                        getPackageName());
                if (buttonId == id){
                    row = i;
                    column = j;
                }
            }
        }

        // If the button was found add the X or O or return an error message
        if (row != -1 && column != -1) {
            TileState state = game.choose(row, column);
            switch(state) {
                case CROSS:
                    ((Button) view).setText("X");
                    message.setText("");
                    break;
                case CIRCLE:
                    ((Button) view).setText("O");
                    message.setText("");
                    break;
                case INVALID:
                    message.setText("That space is already occupied");
                    break;
            }
        }
        GameState gameState = game.won();
        switch (gameState) {
            case DRAW:
                message.setText("Draw");
                break;
            case PLAYER_ONE:
                message.setText("Crosses wins");
                break;
            case PLAYER_TWO:
                message.setText("Circles wins");
                break;
            case IN_PROGRESS:
                // WHY ????
                break;
        }
        Log.d("tic-tac-toe", "tileClicked: "+ gameState);
    }

    public void resetClicked(View view) {
        game = new Game();
        setContentView(R.layout.activity_main);
    }
}

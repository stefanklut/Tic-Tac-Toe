package com.example.stefan.tic_tac_toe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // variables
    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = new Game();
    }

    public void tileClicked(View view) {
        int id = view.getId();
        int column = -1;
        int row = -1;

        // BOARD SIZE ??????
        for (int i=0;i<3;i++) {
            for (int j=0;j<3;j++){
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

        if (row != -1 && column != -1) {
            TileState state = game.choose(row, column);
            switch(state) {
                case CROSS:
                    ((Button) view).setText("X");
                    break;
                case CIRCLE:
                    ((Button) view).setText("O");
                    break;
                case INVALID:
                    // DO WHAT, IGNORING SEEMS TO WORK ??????
                    // do something different
                    break;
            }
        }
    }

    public void resetClicked(View view) {
        game = new Game();
        setContentView(R.layout.activity_main);
    }
}

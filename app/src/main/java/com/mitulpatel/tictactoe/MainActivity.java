package com.mitulpatel.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;

    String player1;
    String player2;

    // Player representation
    // 0 - X
    // 1 - O
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    //    State meanings:
    //    0 - X
    //    1 - O
    //    2 - Null
    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};

    public void Tap(final View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if (!gameActive) {
            gameReset(view);
        }
        if (gameState[tappedImage] == 2) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationZ(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;

            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
            }
            img.animate().translationZBy(1000f).setDuration(300);
        }
        // Check if any player has won
        for (int[] winPosition : winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2) {
                // Somebody has won! - Find out who!
                String winnerStr;
                gameActive = false;
                if (gameState[winPosition[0]] == 0) {
                    winnerStr = player1+" has won the Game";
                    gameReset(view);
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Brain Train");
                    builder.setMessage(player1+" has won the Game");
                    builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
                    builder.setPositiveButton("Reset", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            gameReset(view);
                        }
                    });
                    builder.show();
                } else {
                    winnerStr = player2+" has won the Game";
                    gameReset(view);
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Brain Train");
                    builder.setMessage(player2+" has won the Game");
                    builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
                    builder.setPositiveButton("Reset", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            gameReset(view);
                        }
                    });
                    builder.show();
                }
                // Update the status bar for winner announcement
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
            }


        }

    }

    public void gameReset(View view) {
        gameActive = true;
        activePlayer = 0;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        ((ImageView) findViewById(R.id.zero_img)).setImageResource(0);
        ((ImageView) findViewById(R.id.one_img)).setImageResource(0);
        ((ImageView) findViewById(R.id.two_img)).setImageResource(0);
        ((ImageView) findViewById(R.id.three_img)).setImageResource(0);
        ((ImageView) findViewById(R.id.four_img)).setImageResource(0);
        ((ImageView) findViewById(R.id.five_img)).setImageResource(0);
        ((ImageView) findViewById(R.id.six_img)).setImageResource(0);
        ((ImageView) findViewById(R.id.seven_img)).setImageResource(0);
        ((ImageView) findViewById(R.id.eight_img)).setImageResource(0);



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        player1 = getIntent().getExtras().getString("player1");
        player2 = getIntent().getExtras().getString("player2");

        TextView sign_player1, sign_player2;

        sign_player1 = findViewById(R.id.player1_sign);
        sign_player2 = findViewById(R.id.player2_sign);

        sign_player1.setText(player1+" :- X");
        sign_player2.setText(player2+" :- O");


    }
}


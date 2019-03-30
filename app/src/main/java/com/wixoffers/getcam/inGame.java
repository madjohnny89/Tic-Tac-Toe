package com.wixoffers.getcam;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageFormat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.View;

import de.hdodenhof.circleimageview.CircleImageView;

public class inGame extends AppCompatActivity {
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPostitions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_game_layout);


    }

    public void dropDown(View view) {
        CircleImageView counter = (CircleImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);
            Intent myIntent = getIntent();

            if (activePlayer == 0) {
                counter.setImageBitmap((Bitmap) myIntent.getParcelableExtra("bm1"));
                activePlayer = 1;
            } else {
                counter.setImageBitmap((Bitmap) myIntent.getParcelableExtra("bm2"));
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).alpha(1.0f).setDuration(300);
            for (int[] winingPosition : winningPostitions) {
                if (gameState[winingPosition[0]] == gameState[winingPosition[1]] &&
                        gameState[winingPosition[1]] == gameState[winingPosition[2]] &&
                        gameState[winingPosition[0]] != 2) {
                    if (activePlayer == 1) {
                        new AlertDialog.Builder(this).setTitle("Game Over").setMessage("Player 1 has Won!")
                                
                                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        System.exit(0);
                                    }
                                })
                                .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        activePlayer = 0;
                                        for (int i = 0; i < gameState.length; i++) {
                                            gameState[i] = 2;
                                        }
                                        android.support.v7.widget.GridLayout  gridlayout = findViewById(R.id.gridLayout);
                                        for (int i = 0; i < gridlayout.getChildCount(); i++) {
                                            CircleImageView circleImageView = (CircleImageView) gridlayout.getChildAt(i);
                                            circleImageView.setImageBitmap(null);


                                        }

                                    }
                                }).show();
                    } else {
                        new AlertDialog.Builder(this).setTitle("Game Over").setMessage("Player 2 has Won!")
                                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        System.exit(0);
                                    }
                                })
                                .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        activePlayer = 0;
                                        for (int i = 0; i < gameState.length; i++) {
                                            gameState[i] = 2;
                                        }
                                        GridLayout gridlayout = findViewById(R.id.gridLayout);
                                        for (int i = 0; i < gridlayout.getChildCount(); i++) {
                                            CircleImageView circleImageView = (CircleImageView) gridlayout.getChildAt(i);
                                            circleImageView.setImageBitmap(null);


                                        }

                                    }
                                }).show();

                    }
                }


            }
        }

    }
}

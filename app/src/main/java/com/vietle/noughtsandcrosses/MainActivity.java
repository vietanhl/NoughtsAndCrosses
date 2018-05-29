package com.vietle.noughtsandcrosses;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;
    //2 represents an available play
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    boolean gameIsActive = true;

    public void drop(View view) {

        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        int[][] winning = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
        //0=rick,1= morty

        if (gameState[tappedCounter] == 2 && gameIsActive) {

            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.rick);

                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.morty);

                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);
            for (int[] winningPosition : winning){
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {

                    String winner = "Morty";
                    gameIsActive=false;
                    if (gameState[winningPosition[0]]==0){
                        winner = "Rick";
                        gameIsActive=false;
                    }

//someone wins

                    TextView winningMessage = (TextView) findViewById(R.id.winningMessage);
                    winningMessage.setText(winner + " has won!");
                    LinearLayout layout = findViewById(R.id.play);
                    layout.setVisibility(View.VISIBLE);
                } else {
                    boolean gameIsOver = true;
                    for (int counterState : gameState){
                        if (counterState == 2)
                            gameIsOver=false;
                    }
                    if (gameIsOver){
                        TextView winningMessage = (TextView) findViewById(R.id.winningMessage);
                        winningMessage.setText( "It's a draw!");
                        LinearLayout layout = findViewById(R.id.play);
                        layout.setVisibility(View.VISIBLE);
                    }


                }
            }


        }
    }
    public void playAgainButton(View view) {
        //get rid of text
        LinearLayout layout = findViewById(R.id.play);
        layout.setVisibility(View.INVISIBLE);

        //set the board back
       activePlayer = 0;
       gameIsActive=true;

        for (int i=0; i < gameState.length; i++){
            gameState[i] = 2;
        }
        //reset images
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for (int i=0; i< gridLayout.getChildCount(); i++){

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }


    }





                    @Override
            protected void onCreate (Bundle savedInstanceState){
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

        }

}
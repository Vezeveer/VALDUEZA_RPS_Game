package com.bignerdranch.android.valdueza_rpsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button rockBtn, paperBtn, scissorsBtn, bestOf3btn, bestOf5Btn, bestOf7btn;
    private int turns = 0;
    private int player2choice;
    private TextView messageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rockBtn = findViewById(R.id.rock_btn);
        paperBtn = findViewById(R.id.paper_btn);
        scissorsBtn = findViewById(R.id.scissors_btn);
        bestOf3btn = findViewById(R.id.bestOf3_btn);
        bestOf5Btn = findViewById(R.id.bestOf5_btn);
        bestOf7btn = findViewById(R.id.bestOf7_btn);

        messageTextView = findViewById(R.id.message_here);

        activeBtnRockPaperSci(false);

        bestOf3btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activeBtnRockPaperSci(true);
                activeBtnBestOf(false);
                turns = 3;
                //Log.d("TEST", "turns: " + turns);
            }
        });

        rockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player2Rolled();
                effectRPS();
                if(player2choice == 0){
                    messageTextView.setText("Player 2 picked rock! It's a draw");
                    showMsg("Player 2 picked rock! It's a draw");
                    effectRPS();
                } else if (player2choice == 1){
                    showMsg("Player 2 picked paper! You lost a round");
                } else {
                    showMsg("Player 2 picked scissors! You win this round");
                }
            }
        });

        paperBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player2Rolled();
                effectRPS();
                if(player2choice == 0){
                    showMsg("Player 2 picked rock! You win a round");
                } else if (player2choice == 1){
                    showMsg("Player 2 picked paper! It's a draw");
                } else {
                    showMsg("Player 2 picked scissors! You lose a round");
                }
            }
        });

        scissorsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player2Rolled();
                effectRPS();
                if(player2choice == 0){
                    showMsg("Player 2 picked rock! You lose a round");
                } else if (player2choice == 1){
                    showMsg("Player 2 picked paper! You win a round");
                } else {
                    showMsg("Player 2 picked scissors! It's a draw");
                }
            }
        });

    }

    private void activeBtnRockPaperSci(boolean enabled){
        rockBtn.setEnabled(enabled);
        paperBtn.setEnabled(enabled);
        scissorsBtn.setEnabled(enabled);
    }

    private void activeBtnBestOf(boolean enabled){
        bestOf3btn.setEnabled(enabled);
        bestOf5Btn.setEnabled(enabled);
        bestOf7btn.setEnabled(enabled);
    }

    private void player2Rolled(){
        player2choice = ((int) (Math.random()*(3)));
    }

    private void showMsg(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void effectRPS(){
        activeBtnRockPaperSci(false);
        new CountDownTimer(2000, 1000){
            @Override
            public void onTick(long l) {

            }
            public void onFinish(){
                activeBtnRockPaperSci(true);
            }
        }.start();
    }
}

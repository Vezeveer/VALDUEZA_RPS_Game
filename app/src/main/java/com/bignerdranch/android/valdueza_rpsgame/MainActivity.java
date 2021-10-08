package com.bignerdranch.android.valdueza_rpsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button rockBtn, paperBtn, scissorsBtn,
            bestOf3btn, bestOf5Btn, bestOf7btn;
    private int turns = 0;
    private int round = 1;
    private int player2choice;
    private TextView messageTextView;
    private int roundScore = 0;
    private String p2StrChoice = "";
    private String roundResult = "";

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
        resetMessageText();
        activeBtnRockPaperSci(false);

        bestOf3btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activeBtnRockPaperSci(true);
                activeBtnBestOf(false);
                turns = 3;
            }
        });

        rockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player2Rolled();
                effectRPS("rock");
                roundResult = getRoundResult(0);
                messageTextView.setText("Player 2 - " +
                        p2StrChoice + "\nRound " +
                        roundResult + "\n" + round + "\\" + turns);
                if(roundScore > (turns - roundScore)){
                    messageTextView.setText("Congrats. You win!");
                }
            }
        });

        paperBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player2Rolled();
                effectRPS("paper");
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
                effectRPS("scissors");
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

    private String getRoundResult(int player1){
        if(player1 == player2choice){
            return "Draw";
        } else if (player1 == 0 && player2choice == 1 ||
                    player1 == 1 && player2choice == 2 ||
                    player1 == 2 && player2choice == 0){
            return "Loss";
        } else {
            roundScore++;
            return "Won";
        }
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
        if(player2choice == 0){
            p2StrChoice = "Rock";
        } else if (player2choice == 1){
            p2StrChoice = "Paper";
        } else {
            p2StrChoice = "Scissors";
        }
    }

    private void showMsg(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void effectRPS(String btn){
        if(btn == "rock"){
            rockBtn.setEnabled(false);
        } else if (btn == "paper"){
            paperBtn.setEnabled(false);
        } else {
            scissorsBtn.setEnabled(false);
        }
        rockBtn.setClickable(false);
        paperBtn.setClickable(false);
        scissorsBtn.setClickable(false);
        new CountDownTimer(2000, 1000){
            @Override
            public void onTick(long l) {

            }
            public void onFinish(){
                paperBtn.setClickable(true);
                scissorsBtn.setClickable(true);
                rockBtn.setClickable(true);
                paperBtn.setEnabled(true);
                scissorsBtn.setEnabled(true);
                rockBtn.setEnabled(true);
            }
        }.start();
    }

    private void resetMessageText(){
        messageTextView.setText("Player 1 - You \nPlayer 2 - Computer \nPick a \"Best of\" category");
    }
}

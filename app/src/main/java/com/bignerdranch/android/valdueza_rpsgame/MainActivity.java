package com.bignerdranch.android.valdueza_rpsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button rockBtn, paperBtn, scissorsBtn,
            bestOf3Btn, bestOf5Btn, bestOf7Btn;
    private int turns = 0;
    private int round = 1;
    private int player2choice;
    private TextView messageTextView, winsTextView,
            p1Btn, p2Btn;
    private int p1RoundScore = 0;
    private int p2RoundScore = 0;
    private int wins = 0;
    private String p2StrChoice = "";
    private String roundResult = "";
    private boolean roundComplete = false;
    private LinearLayout linearLayoutMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rockBtn = findViewById(R.id.rock_btn);
        paperBtn = findViewById(R.id.paper_btn);
        scissorsBtn = findViewById(R.id.scissors_btn);
        bestOf3Btn = findViewById(R.id.bestOf3_btn);
        bestOf5Btn = findViewById(R.id.bestOf5_btn);
        bestOf7Btn = findViewById(R.id.bestOf7_btn);

        winsTextView = findViewById(R.id.wins_text);
        messageTextView = findViewById(R.id.message_here);
        p1Btn = findViewById(R.id.p1_btn);
        p2Btn = findViewById(R.id.p2_btn);
        resetMessageText();
        activeBtnRockPaperSci(false);
        linearLayoutMain = findViewById(R.id.layout_main);

        p1Btn.setClickable(false);
        p2Btn.setClickable(false);
        p1Btn.setBackgroundColor(Color.parseColor("#2643b5"));
        p2Btn.setBackgroundColor(Color.parseColor("#b5ae26"));

        bestOf3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                messageTextView.setText("Best of 3\nPlay your hand");
                activeBtnRockPaperSci(true);
                activeBtnBestOf(false);
                resetAll();
                turns = 3;
                linearLayoutMain.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
        });

        bestOf5Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                messageTextView.setText("Best of 5\nPlay your hand");
                activeBtnRockPaperSci(true);
                activeBtnBestOf(false);
                resetAll();
                turns = 5;
                linearLayoutMain.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
        });

        bestOf7Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                messageTextView.setText("Best of 7\nPlay your hand");
                activeBtnRockPaperSci(true);
                activeBtnBestOf(false);
                resetAll();
                turns = 7;
                linearLayoutMain.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
        });

        rockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player2Rolled();
                updateRound(0, "rock");
            }
        });

        paperBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player2Rolled();
                updateRound(1, "paper");
            }
        });

        scissorsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player2Rolled();
                updateRound(2,"scissors");
            }
        });

    }

    private void updateRound(int btnPosition, String btnPressed) {
        roundResult = getRoundResult(btnPosition);
        String strOpeningMsg = "Player 2 - " +
                p2StrChoice + "\nRound " +
                roundResult + "\n" + round + "\\" + turns;
        String strWinMsg = "Player 2 - " +
                p2StrChoice + "\nCongrats. You win!";
        String strLostMessage = "Player 2 - " +
                p2StrChoice + "\nYou Lost the game.";
        String strDrawMessage = "Player 2 - " +
                p2StrChoice +"\nIt's a DRAW. No one wins";
        String strP1RoundScore = "P1: "+p1RoundScore;
        String strP2RoundScore = "P2: "+p2RoundScore;

        messageTextView.setText(strOpeningMsg);

        if(p1RoundScore > (turns - p1RoundScore) ||
                (round == turns && p2RoundScore < p1RoundScore)){
            wins++;
            winsTextView.setText("Player 1:\n" + wins + " Wins");
            linearLayoutMain.setBackgroundColor(Color.parseColor("#7aeb34"));
            roundComplete = true;
            messageTextView.setText(strWinMsg);
            activeBtnRockPaperSci(false);
            activeBtnBestOf(true);
        } else if(p2RoundScore > (turns - p2RoundScore) ||
                (round == turns && p2RoundScore > p1RoundScore)){
            messageTextView.setText(strLostMessage);
            activeBtnRockPaperSci(false);
            activeBtnBestOf(true);
            linearLayoutMain.setBackgroundColor(Color.parseColor("#eb4034"));
            roundComplete = true;
        } else if(round == turns){
            messageTextView.setText(strDrawMessage);
            activeBtnRockPaperSci(false);
            activeBtnBestOf(true);
            linearLayoutMain.setBackgroundColor(Color.parseColor("#a0b9e8"));
            roundComplete = true;
        }
        effectRPS(btnPressed);
        p1Btn.setText(strP1RoundScore);
        p2Btn.setText(strP2RoundScore);
        round++;
    }

    private void resetAll() {
        round = 1;
        p1RoundScore = 0;
        p2RoundScore = 0;
        roundComplete = false;
        p1Btn.setText("P1");
        p2Btn.setText("P2");
    }

    private String getRoundResult(int player1){
        if(player1 == player2choice){
            return "Draw";
        } else if (player1 == 0 && player2choice == 1 ||
                    player1 == 1 && player2choice == 2 ||
                    player1 == 2 && player2choice == 0){
            p2RoundScore++;
            return "Loss";
        } else {
            p1RoundScore++;
            return "Won";
        }
    }

    private void activeBtnRockPaperSci(boolean enabled){
        rockBtn.setEnabled(enabled);
        paperBtn.setEnabled(enabled);
        scissorsBtn.setEnabled(enabled);
        rockBtn.setClickable(enabled);
        paperBtn.setClickable(enabled);
        scissorsBtn.setClickable(enabled);
    }

    private void activeBtnBestOf(boolean enabled){
        bestOf3Btn.setEnabled(enabled);
        bestOf5Btn.setEnabled(enabled);
        bestOf7Btn.setEnabled(enabled);
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
        if(btn.equals("rock")){
            rockBtn.setEnabled(false);
        } else if (btn.equals("paper")){
            paperBtn.setEnabled(false);
        } else {
            scissorsBtn.setEnabled(false);
        }
        rockBtn.setClickable(false);
        paperBtn.setClickable(false);
        scissorsBtn.setClickable(false);
        if(!roundComplete){
            new CountDownTimer(1000, 1000){
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
    }

    private void resetMessageText(){
        String firstMsg = "Player 1 - You \nPlayer 2 - Computer" +
                "\nPick a \"Best of\" category";
        messageTextView.setText(firstMsg);
    }
}

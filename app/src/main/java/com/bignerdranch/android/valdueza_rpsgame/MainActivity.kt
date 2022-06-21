package com.bignerdranch.android.valdueza_rpsgame

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.os.CountDownTimer
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var turns = 0
    private var round = 1
    private var player2choice = 0
    private var p1RoundScore = 0
    private var p2RoundScore = 0
    private var wins = 0
    private var p2StrChoice = ""
    private var roundResult = ""
    private var roundComplete = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resetMessageText()
        activeBtnRockPaperSci(false)
        p1_btn.isClickable = false
        p2_btn.isClickable = false
        p1_btn.setBackgroundColor(Color.parseColor("#2643b5"))
        p2_btn.setBackgroundColor(Color.parseColor("#b5ae26"))
        bestOf3_btn?.setOnClickListener(View.OnClickListener {
            message_here.text = "Best of 3\nPlay your hand"
            activeBtnRockPaperSci(true)
            activeBtnBestOf(false)
            resetAll()
            turns = 3
            layout_main.setBackgroundColor(Color.parseColor("#FFFFFF"))
        })
        bestOf5_btn.setOnClickListener(View.OnClickListener {
            message_here?.text = "Best of 5\nPlay your hand"
            activeBtnRockPaperSci(true)
            activeBtnBestOf(false)
            resetAll()
            turns = 5
            layout_main.setBackgroundColor(Color.parseColor("#FFFFFF"))
        })
        bestOf7_btn.setOnClickListener(View.OnClickListener {
            message_here?.text = "Best of 7\nPlay your hand"
            activeBtnRockPaperSci(true)
            activeBtnBestOf(false)
            resetAll()
            turns = 7
            layout_main.setBackgroundColor(Color.parseColor("#FFFFFF"))
        })
        rock_btn.setOnClickListener(View.OnClickListener {
            player2Rolled()
            updateRound(0, "rock")
        })
        paper_btn.setOnClickListener(View.OnClickListener {
            player2Rolled()
            updateRound(1, "paper")
        })
        scissors_btn.setOnClickListener(View.OnClickListener {
            player2Rolled()
            updateRound(2, "scissors")
        })
    }

    private fun updateRound(btnPosition: Int, btnPressed: String) {
        roundResult = getRoundResult(btnPosition)
        val strOpeningMsg = """
            Player 2 - $p2StrChoice
            Round $roundResult
            $round\$turns
            """.trimIndent()
        val strWinMsg = """
            Player 2 - $p2StrChoice
            Congrats. You win!
            """.trimIndent()
        val strLostMessage = """
            Player 2 - $p2StrChoice
            You Lost the game.
            """.trimIndent()
        val strDrawMessage = """
            Player 2 - $p2StrChoice
            It's a DRAW. No one wins
            """.trimIndent()
        val strP1RoundScore = "P1: $p1RoundScore"
        val strP2RoundScore = "P2: $p2RoundScore"
        message_here!!.text = strOpeningMsg
        if (p1RoundScore > turns - p1RoundScore ||
            round == turns && p2RoundScore < p1RoundScore
        ) {
            wins++
            wins_text!!.text = "Player 1:\n$wins Wins"
            layout_main!!.setBackgroundColor(Color.parseColor("#7aeb34"))
            roundComplete = true
            message_here!!.text = strWinMsg
            activeBtnRockPaperSci(false)
            activeBtnBestOf(true)
        } else if (p2RoundScore > turns - p2RoundScore ||
            round == turns && p2RoundScore > p1RoundScore
        ) {
            message_here!!.text = strLostMessage
            activeBtnRockPaperSci(false)
            activeBtnBestOf(true)
            layout_main!!.setBackgroundColor(Color.parseColor("#eb4034"))
            roundComplete = true
        } else if (round == turns) {
            message_here!!.text = strDrawMessage
            activeBtnRockPaperSci(false)
            activeBtnBestOf(true)
            layout_main!!.setBackgroundColor(Color.parseColor("#a0b9e8"))
            roundComplete = true
        }
        effectRPS(btnPressed)
        p1_btn!!.text = strP1RoundScore
        p2_btn!!.text = strP2RoundScore
        round++
    }

    private fun resetAll() {
        round = 1
        p1RoundScore = 0
        p2RoundScore = 0
        roundComplete = false
        p1_btn!!.text = "P1"
        p2_btn!!.text = "P2"
    }

    private fun getRoundResult(player1: Int): String {
        return if (player1 == player2choice) {
            "Draw"
        } else if (player1 == 0 && player2choice == 1 || player1 == 1 && player2choice == 2 || player1 == 2 && player2choice == 0) {
            p2RoundScore++
            "Loss"
        } else {
            p1RoundScore++
            "Won"
        }
    }

    private fun activeBtnRockPaperSci(enabled: Boolean) {
        rock_btn!!.isEnabled = enabled
        paper_btn!!.isEnabled = enabled
        scissors_btn!!.isEnabled = enabled
        rock_btn!!.isClickable = enabled
        paper_btn!!.isClickable = enabled
        scissors_btn!!.isClickable = enabled
    }

    private fun activeBtnBestOf(enabled: Boolean) {
        bestOf3_btn!!.isEnabled = enabled
        bestOf5_btn!!.isEnabled = enabled
        bestOf7_btn!!.isEnabled = enabled
    }

    private fun player2Rolled() {
        player2choice = (Math.random() * 3).toInt()
        p2StrChoice = if (player2choice == 0) {
            "Rock"
        } else if (player2choice == 1) {
            "Paper"
        } else {
            "Scissors"
        }
    }

    private fun showMsg(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun effectRPS(btn: String) {
        if (btn == "rock") {
            rock_btn!!.isEnabled = false
        } else if (btn == "paper") {
            paper_btn!!.isEnabled = false
        } else {
            scissors_btn!!.isEnabled = false
        }
        rock_btn!!.isClickable = false
        paper_btn!!.isClickable = false
        scissors_btn!!.isClickable = false
        if (!roundComplete) {
            object : CountDownTimer(1000, 1000) {
                override fun onTick(l: Long) {}
                override fun onFinish() {
                    paper_btn!!.isClickable = true
                    scissors_btn!!.isClickable = true
                    rock_btn!!.isClickable = true
                    paper_btn!!.isEnabled = true
                    scissors_btn!!.isEnabled = true
                    rock_btn!!.isEnabled = true
                }
            }.start()
        }
    }

    private fun resetMessageText() {
        val firstMsg = """Player 1 - You 
Player 2 - Computer
Pick a "Best of" category"""
        message_here!!.text = firstMsg
    }
}
package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random

class SecondActivity : AppCompatActivity() {

    lateinit var rockImage: ImageView
    lateinit var paperImage: ImageView
    lateinit var scissorsImage: ImageView
    lateinit var userChoiceImage: ImageView
    lateinit var computerChoiceImage: ImageView
    lateinit var startBtn: Button
    lateinit var secondActRetryBtn: Button
    lateinit var resultTextView: TextView

    val images: MutableList<Int> = mutableListOf()
    var userChoice: Int = 0
    var computerChoice: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val actionBar = supportActionBar
        actionBar!!.title = "Ro Sham Bo"

        actionBar.setDisplayHomeAsUpEnabled(true)

        rockImage = findViewById(R.id.rockImage)
        paperImage = findViewById(R.id.paperImage)
        scissorsImage = findViewById(R.id.scissorsImage)
        userChoiceImage = findViewById(R.id.userChoiceImage)
        computerChoiceImage = findViewById(R.id.computerChoiceImage)
        startBtn = findViewById(R.id.startBtn)
        secondActRetryBtn = findViewById(R.id.secondActRetryBtn)
        resultTextView = findViewById(R.id.resultTextView)

        images.add(R.drawable.rock)
        images.add(R.drawable.paper)
        images.add(R.drawable.scissors)

        var currentUserImage = userChoiceImage.setImageResource(R.drawable.ic_blank)

        if (currentUserImage == userChoiceImage.setImageResource(R.drawable.ic_blank)){
            startBtn.isClickable = false
        }
    }

    fun useRock(view: View) {
        userChoiceImage.setImageResource(R.drawable.rock)
        startBtn.isClickable = true
        userChoice = 1
    }

    fun usePaper(view: View) {
        userChoiceImage.setImageResource(R.drawable.paper)
        startBtn.isClickable = true
        userChoice = 2
    }

    fun useScissors(view: View) {
        userChoiceImage.setImageResource(R.drawable.scissors)
        startBtn.isClickable = true
        userChoice = 3
    }

    fun startGame(view: View) {
        getComputerChoice()
        startBtn.isClickable = false
        rockImage.isClickable = false
        paperImage.isClickable = false
        scissorsImage.isClickable = false

        resultCheck()
        startBtn.visibility = GONE
        secondActRetryBtn.visibility = VISIBLE
    }

    private fun resultCheck() {
        if (userChoice == computerChoice) {
            val message: String = "Its a Tie!"
            resultTextView.text = message
        } else if (userChoice == 1) {
            val message: String = if (userChoice == 1 && computerChoice == 3) "You win!" else "You lose!"
            resultTextView.text = message
        } else if (userChoice == 2) {
            val message: String = if (userChoice == 2 && computerChoice == 1) "You win!" else "You lose!"
            resultTextView.text = message
        } else if (userChoice == 3) {
            val message: String = if (userChoice == 3 && computerChoice == 2) "You win!" else "You lose!"
            resultTextView.text = message
        }
    }

    private fun getComputerChoice() {
        val random = Random.nextInt(3)
        computerChoiceImage.setImageResource(images.elementAt(random))
        computerChoice = random + 1
    }

    fun refreshActivity(view: View) {
        finish()
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
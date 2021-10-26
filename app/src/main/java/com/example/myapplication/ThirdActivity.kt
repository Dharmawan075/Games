package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.VISIBLE
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class ThirdActivity : AppCompatActivity() {

    lateinit var maxNumberTextView: TextView
    lateinit var outcomeTextView: TextView
    lateinit var userEditText: EditText
    lateinit var btn20: Button
    lateinit var btn50: Button
    lateinit var btn100: Button
    lateinit var guessBtn: Button
    lateinit var thirdActRetryBtn: Button

    var numberGenerated: Int = 0
    var currentState: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        val actionBar = supportActionBar
        actionBar!!.title = "Guess The Number"

        actionBar.setDisplayHomeAsUpEnabled(true)

        maxNumberTextView = findViewById(R.id.maxNumberTextView)
        outcomeTextView = findViewById(R.id.outcomeTextView)
        userEditText = findViewById(R.id.userEditText)
        btn20 = findViewById(R.id.btn20)
        btn50 = findViewById(R.id.btn50)
        btn100 = findViewById(R.id.btn100)
        guessBtn = findViewById(R.id.btnGuess)
        thirdActRetryBtn = findViewById(R.id.thirdActRetryBtn)

        guessBtn.setAlpha(0.5f)
        if (currentState == 0) {
            guessBtn.isClickable = false
        }

        btn20.setOnClickListener {
            numberGenerated = randomNumberGenerator(21)
            Toast.makeText(this, "Max Number is 20", Toast.LENGTH_SHORT).show()
            currentState == 1
            guessBtn.isClickable = true
            guessBtn.setAlpha(1f)
            maxNumberTextView.text = "Max Number: 20"
        }

        btn50.setOnClickListener {
            numberGenerated = randomNumberGenerator(51)
            Toast.makeText(this, "Max Number is 50", Toast.LENGTH_SHORT).show()
            currentState == 1
            guessBtn.isClickable = true
            guessBtn.setAlpha(1f)
            maxNumberTextView.text = "Max Number: 50"
        }

        btn100.setOnClickListener {
            numberGenerated = randomNumberGenerator(101)
            Toast.makeText(this, "Max Number is 100", Toast.LENGTH_SHORT).show()
            currentState == 1
            guessBtn.isClickable = true
            guessBtn.setAlpha(1f)
            maxNumberTextView.text = "Max Number: 100"
        }
    }

    private fun randomNumberGenerator(x: Int): Int {
        val randomNumberGenerated = Random.nextInt(x)
        return randomNumberGenerated
    }

    fun refreshActivity(view: View) {
        finish()
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }

    fun checkGuess(view: View) {
        var userInput = userEditText.text.toString()
        var integerUserInput = userInput.toInt()
        var comparedNumber = numberGenerated

        if (integerUserInput == comparedNumber) {
            outcomeTextView.text = "You're Right! \n The Random Number is $comparedNumber"
            userEditText.onEditorAction(EditorInfo.IME_ACTION_DONE) // Remove soft keyboard
            thirdActRetryBtn.visibility = VISIBLE
        } else if (integerUserInput > comparedNumber) {
            outcomeTextView.text = "Guess Lower!"
        } else if (integerUserInput < comparedNumber) {
            outcomeTextView.text = "Guess Higher!"
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
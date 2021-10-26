package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var welcomeTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportActionBar?.title = "Home"

        auth = FirebaseAuth.getInstance()

        val user: String? = intent.getStringExtra("user")
        welcomeTextView = findViewById(R.id.welcomeTextView)
        welcomeTextView.text = "Welcome $user"
    }

    fun toMainActivity(view: View){
        val toMain = Intent(this, MainActivity::class.java)
        startActivity(toMain)
    }

    fun toSecondActivity(view: View) {
        val toSecond = Intent(this, SecondActivity::class.java)
        startActivity(toSecond)
    }

    fun toThirdActivity(view: View) {
        val toThird = Intent(this, ThirdActivity::class.java)
        startActivity(toThird)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.signOut){
            auth.signOut()
            finish()

            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)

            return true
        }
        return true
    }
}
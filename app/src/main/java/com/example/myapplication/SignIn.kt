package com.example.myapplication

import android.content.ContentValues.TAG
import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignIn : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    lateinit var emailSignIn: EditText
    lateinit var passwordSignIn: EditText
    lateinit var signInBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        supportActionBar?.hide()

        auth = Firebase.auth

        emailSignIn = findViewById(R.id.emailSignIn)
        passwordSignIn = findViewById(R.id.passwordSignIn)
        signInBtn = findViewById(R.id.signInBtn)

        signInBtn.setOnClickListener {
            val email = emailSignIn.text.toString()
            val password = passwordSignIn.text.toString()

            if(email.isEmpty()) {
                Toast.makeText(this, "Name is Empty", Toast.LENGTH_SHORT).show()
                emailSignIn.requestFocus()
            } else if (password.isEmpty()) {
                Toast.makeText(this, "Password is Empty", Toast.LENGTH_SHORT).show()
                passwordSignIn.requestFocus()
            } else {
                signInUser(email, password)
            }
        }
    }

    private fun signInUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent (this, HomeActivity::class.java)
                    intent.putExtra("user", email)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Error Occurred! Please Check Again!", Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun toSignUpActivity(view: View) {
        val toSignUp = Intent (this, SignUp::class.java)
        startActivity(toSignUp)
    }


}
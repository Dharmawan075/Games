package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Patterns.EMAIL_ADDRESS
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.regex.Pattern

class SignUp : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var dbRef: DatabaseReference
    lateinit var signUpBtn: Button

    lateinit var nameSignUp: EditText
    lateinit var emailSignUp: EditText
    lateinit var passwordSignUp: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val actionBar = supportActionBar
        actionBar!!.title = ""
        actionBar.setDisplayHomeAsUpEnabled(true)

        auth = FirebaseAuth.getInstance()
        signUpBtn = findViewById(R.id.signUpBtn)

        nameSignUp = findViewById(R.id.nameSignUp)
        emailSignUp = findViewById(R.id.emailSignUp)
        passwordSignUp = findViewById(R.id.passwordSignUp)

        signUpBtn.setOnClickListener {
            val name = nameSignUp.text.toString()
            val email = emailSignUp.text.toString()
            val password = passwordSignUp.text.toString()

            if (name.isEmpty()) {
                Toast.makeText(this, "Name is Empty", Toast.LENGTH_SHORT).show()
                nameSignUp.requestFocus()
            } else if (email.isEmpty()) {
                Toast.makeText(this, "Email is Empty", Toast.LENGTH_SHORT).show()
                emailSignUp.requestFocus()
            } else if (password.length <= 6) {
                Toast.makeText(this, "Password Must > 6 Characters", Toast.LENGTH_SHORT).show()
                passwordSignUp.requestFocus()
            } else {
                signUpUser(name, email, password)
            }
        }
    }

    private fun signUpUser(name: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    addUserToDb(name, email, auth.currentUser?.uid!!)

                    Toast.makeText(this, "User Created Successfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent (this, SignIn::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Error Occurred! Please Check Again!", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun addUserToDb(name: String, email: String, uid: String) {
        dbRef = FirebaseDatabase.getInstance().getReference()
        dbRef.child("User").child(uid).setValue(User(name, email, uid))
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
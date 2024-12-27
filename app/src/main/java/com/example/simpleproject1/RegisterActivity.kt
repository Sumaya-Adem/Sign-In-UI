package com.example.simpleproject1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.newFixedThreadPoolContext

class RegisterActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        firebaseAuth = FirebaseAuth.getInstance()

        val emailRegister : TextInputEditText = findViewById(R.id.editTextEmail)
        val passwordRegister : TextInputEditText = findViewById(R.id.editTextPassword2)
        val SignUpButton : Button = findViewById(R.id.button_sign_up)
        val editTextName : EditText = findViewById(R.id.editTextName)


        SignUpButton.setOnClickListener {
//            Toast.makeText(this, "Welcome ${editTextName.text}", Toast.LENGTH_SHORT).show()
            val email = emailRegister.text.toString()
            val password = passwordRegister.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show()
            } else{
                registerUser(email, password)
            }



        }
    }
    private fun registerUser(email: String, password: String){
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, SignInActivity::class.java)
                    startActivity(intent)
                    finish()
                    val user: FirebaseUser? = firebaseAuth.currentUser
                    Toast.makeText(this, "Registration successful! Welcome, ${user?.email}", Toast.LENGTH_SHORT).show()
                    // You can navigate to another activity here
                } else {
                    // Registration failed, show error message
                    Toast.makeText(this, "Registration failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }


    }


}
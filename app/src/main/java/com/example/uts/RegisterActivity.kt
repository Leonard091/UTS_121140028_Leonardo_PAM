package com.example.uts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.uts.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val auth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerButton.setOnClickListener {
            registerUser()
        }

        binding.loginButton.setOnClickListener {
            val intent2 = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent2)
            finish()
        }
    }

    private fun registerUser() {
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Register failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@RegisterActivity, RegisterActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
    }
}
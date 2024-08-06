package com.example.celcious

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.signup_lay)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val usernameval = findViewById<EditText>(R.id.signup_username_id)
        val emailval = findViewById<EditText>(R.id.signup_email_id)
        val passval1 = findViewById<EditText>(R.id.signup_password_id)
        val passval2 = findViewById<EditText>(R.id.signup_confirm_password_id)
        val cityval = findViewById<EditText>(R.id.signup_city_id)
        val signupval = findViewById<Button>(R.id.signup_button_id)
        val olduserval = findViewById<Button>(R.id.olduser_button)

        val sharedPreferences= getSharedPreferences("userpref", MODE_PRIVATE)
        val editor= sharedPreferences.edit()

        fun isValidEmail(email: String): Boolean {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        signupval.setOnClickListener() {

            val username = usernameval.text.toString()
            val pass1 = passval1.text.toString()
            val pass2 = passval2.text.toString()
            val city = cityval.text.toString()
            val email = emailval.text.toString()



            if (username.isNotEmpty()) {
                if (pass1.isNotEmpty()) {
                    if (pass1 == pass2) {
                        if (email.isNotEmpty()) {
                            if(isValidEmail(email)) {
                                if (city.isNotEmpty()) {
                                    editor.putString("username", username)
                                    editor.putString("password", pass1)
                                    editor.putString("email", email)
                                    editor.putString("city", city)
                                    editor.apply()

                                    val intent = Intent(this, Home::class.java)
                                    startActivity(intent)
                                } else {
                                    cityval.error = "Enter the city"
                                }
                            }else{
                                emailval.error="enter a valid email"
                            }
                        } else {
                            emailval.error = "Enter a valid email"
                        }
                    } else {
                        passval2.error = "Password is not matching"
                    }

                } else {
                    passval1.error = "password must not be empty"
                }
            } else {
                usernameval.error = "Enter the username"
            }
        }
        olduserval.setOnClickListener(){
            val intent=Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
}
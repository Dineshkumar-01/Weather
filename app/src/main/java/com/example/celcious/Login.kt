package com.example.celcious

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.login)

        val emailval=findViewById<EditText>(R.id.login_email_id)
        val passval=findViewById<EditText>(R.id.login_password_id)
        val loginbutton=findViewById<Button>(R.id.login_button)
        val newuserval=findViewById<Button>(R.id.login_signup_button)
        val forgotpassval=findViewById<Button>(R.id.forgot_button_id)

        val sharedPreferences = getSharedPreferences("userpref", MODE_PRIVATE)
        fun isValidEmail(email: String): Boolean {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }



        loginbutton.setOnClickListener() {
            val storedemail = sharedPreferences.getString("email", null)
            val storedpass = sharedPreferences.getString("password", null)

            val email = emailval.text.toString().trim()
            val pass = passval.text.toString().trim()
            if(email.isNotBlank()) {
                if(isValidEmail(email)) {
                    if(pass.isNotBlank()){
                        if(storedemail==email&&storedpass==pass){
                            val intent=Intent(this,Home::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this, "Username or Password is wrong", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        passval.error="enter the password to verify"
                    }

                }else{
                    emailval.error="Enter a valid email"
                }
            }else{
                emailval.error="Enter a email"
            }
        }

        newuserval.setOnClickListener(){
            val intent=Intent(this,signup::class.java)
            startActivity(intent)
        }

        forgotpassval.setOnClickListener(){
            val intent=Intent(this,Home::class.java)
            startActivity(intent)
        }








    }
}
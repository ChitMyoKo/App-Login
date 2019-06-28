package com.asg.june28asg

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    companion object{
        fun newIntent(context: Context):Intent
        {
            var intent = Intent(context,LoginActivity::class.java)
            return intent
        }
    }

    private lateinit var userName : String
    private lateinit var password : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnLogin.setOnClickListener {
            userName = edUserName.text.toString()
            password = edPassword.text.toString()

            if(userName.equals("") && password.equals(""))
            {
                edUserName?.error = "User name required!"
                edPassword?.error = "Password required!"
            }
            else if(!userName.equals("") && password.equals(""))
            {
                edPassword?.error = "Password required!"
            }
            else if(userName.equals("") && !password.equals(""))
            {
                edUserName?.error = "User name required!"
            }
            else
            {
                if(userName.equals("koko") && password.equals("koko"))
                {
                    Toast.makeText(this,"Login Successful!",Toast.LENGTH_LONG).show()
                    startActivity(MainActivity.newIntent(this))
                }
                else
                {
                    Toast.makeText(this,"Login Failed!",Toast.LENGTH_LONG).show()
                }
            }

        }
    }
}

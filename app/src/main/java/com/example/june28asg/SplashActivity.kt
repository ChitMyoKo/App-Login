package com.example.june28asg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.asg.june28asg.LoginActivity
import com.asg.june28asg.MainActivity
import com.asg.june28asg.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        lottieSplash.setAnimation("data.json")
        lottieSplash.playAnimation()
        lottieSplash.loop(true)

        val handler = Handler()
        handler.postDelayed({
            startActivity(LoginActivity.newIntent(this))
            finish()
        },3000)
    }
}

package com.andor.watchit.screens.splash

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.andor.watchit.R
import com.andor.watchit.screens.common.controller.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    private val splashTime = 500L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onStart() {
        super.onStart()
        val animation = AnimationUtils.loadAnimation(this, R.anim.nav_default_enter_anim)
        animation.duration = splashTime
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                startMainActivity()
            }

            override fun onAnimationStart(animation: Animation?) {
            }
        })
        splashText.startAnimation(animation)
    }

    private fun startMainActivity() {
        val i = MainActivity.getIntent(this@SplashActivity)
        startActivity(i)
        finish()
    }
}

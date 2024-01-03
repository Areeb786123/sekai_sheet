package com.areeb.sekaisheet.ui.splashScreen.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.areeb.sekaisheet.R
import com.areeb.sekaisheet.ui.spiningWheel.activity.SpinnerActivity

class SplashScreenActivity : AppCompatActivity() {
    companion object {
        private const val SPLASH_SCREEN_DELAY = 2000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val handler = Handler(mainLooper)
        handler.postDelayed({
            reDirectToSpinnerActivity()
        }, SPLASH_SCREEN_DELAY)
    }

    private fun reDirectToSpinnerActivity() {
        val intent = Intent(this, SpinnerActivity::class.java)
        startActivity(intent)
    }
}

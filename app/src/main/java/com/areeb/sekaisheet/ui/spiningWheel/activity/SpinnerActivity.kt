package com.areeb.sekaisheet.ui.spiningWheel.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.areeb.sekaisheet.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpinnerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner)
    }
}

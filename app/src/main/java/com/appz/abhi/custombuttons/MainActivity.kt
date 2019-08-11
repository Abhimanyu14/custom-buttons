package com.appz.abhi.custombuttons

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.appz.abhi.custombuttons.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {


    // Data binding
    private lateinit var mainActivityBinding: MainActivityBinding


    // On activity creation starting
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Log message
        Log.e(TAG, "onCreate")

        // Set activity layout
        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.main_activity)
    }


    companion object {


        // Logging constant
        private val TAG = MainActivity::class.java.simpleName
    }
}

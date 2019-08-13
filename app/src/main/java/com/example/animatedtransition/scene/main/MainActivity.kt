package com.example.animatedtransition.scene.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.animatedtransition.R
import com.example.animatedtransition.scene.main.applications.ApplicationsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportFragmentManager.fragments.isEmpty()) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, ApplicationsFragment())
                    .commit()
        }
    }
}

package com.example.skillbox_hw_quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.skillbox_hw_quiz.databinding.MainActivityBinding
import com.example.skillbox_hw_quiz.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, MainFragment.newInstance())
//                .commitNow()
//        }
    }

}
package com.rajdeepsingh.birthdayreminderapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rajdeepsingh.birthdayreminderapp.R
import com.rajdeepsingh.birthdayreminderapp.adapters.BirthdayRecyclerViewAdapter
import com.rajdeepsingh.birthdayreminderapp.model.BirthdayModelDataClass

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var bottomNavigationView : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        navController= Navigation.findNavController(this,R.id.activity_main_nav_host_fragment)
        setupWithNavController(bottomNavigationView,navController)

    }
}
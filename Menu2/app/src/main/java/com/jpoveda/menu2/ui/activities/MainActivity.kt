package com.jpoveda.menu2.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import com.jpoveda.menu2.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(myToolBar)

        val toggle = ActionBarDrawerToggle(
            this,
            myDrawerLayout,
            myToolBar,
            R.string.txt_open,
            R.string.txt_close
             )
         myDrawerLayout.addDrawerListener(toggle)
         toggle.syncState()

    }
}
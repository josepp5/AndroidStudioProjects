package com.jpoveda.menu2.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jpoveda.menu2.Application
import com.jpoveda.menu2.R
import com.jpoveda.menu2.ui.fragments.SettingsFragment
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setFragment()
    }

    private fun setFragment(){
        val fragment = SettingsFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_holder, fragment)
        transaction.addToBackStack(null)

        transaction.commit()
        //isFragmentOneLoaded = false
    }
}
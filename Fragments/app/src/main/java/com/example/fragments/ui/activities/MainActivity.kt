package com.example.fragments.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fragments.R
import com.example.fragments.ui.fragments.FragmentA
import com.example.fragments.ui.fragments.FragmentB
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var isFragmentOneLoaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setView()
        setListener()
    }

    private fun setView() {
        showFragmentA()
    }

    private fun setListener() {
        button.setOnClickListener {
            if (isFragmentOneLoaded) {
                showFragmentB()
            } else {
                showFragmentA()
            }
        }
    }

    // How to load a Fragment
    private fun showFragmentA() {
        // Initializes
        val fragment = FragmentA()
        // Sends info
        val bundle = Bundle()
        bundle.putString("name", "Jose Maria")
        fragment.arguments = bundle
        // Starts the fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment, fragment)
        transaction.addToBackStack(null)

        transaction.commit()
        isFragmentOneLoaded = true
    }

    private fun showFragmentB() {
        val fragment = FragmentB()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment, fragment)
        transaction.addToBackStack(null)

        transaction.commit()
        isFragmentOneLoaded = false
    }
}
package com.jpoveda.dbadapter.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jpoveda.dbadapter.R
import com.jpoveda.dbadapter.model.DBHelper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dbHelper = DBHelper(this,null)
        dbHelper.addAmigo("Juan", "del amor")
    }
}
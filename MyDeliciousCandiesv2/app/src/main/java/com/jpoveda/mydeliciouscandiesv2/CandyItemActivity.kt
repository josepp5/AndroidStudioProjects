package com.jpoveda.mydeliciouscandiesv2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.jpoveda.mydeliciouscandiesv2.data.candy.Candy
import com.jpoveda.mydeliciouscandiesv2.data.candy.CandyFileDataSource
import com.jpoveda.mydeliciouscandiesv2.data.candy.ICandyDataSource
import kotlinx.android.synthetic.main.candy_item_activity.*

class CandyItemActivity : AppCompatActivity() {

    var items = ArrayList<Candy>()
    lateinit var dataSource: ICandyDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.candy_item_activity)
        initView()
    }

    fun initView(){
        items = ArrayList(dataSource.getList())
        txtNombre.text = items[1].name
    }

}
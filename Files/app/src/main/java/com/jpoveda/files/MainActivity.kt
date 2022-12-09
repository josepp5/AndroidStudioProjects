package com.jpoveda.files

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jpoveda.files.data.persons.IPersonDataSource
import com.jpoveda.files.data.persons.Person
import com.jpoveda.files.data.persons.PersonFileDataSource
import com.jpoveda.files.ui.adapters.PersonAdapterList
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var personDataSource: IPersonDataSource
    lateinit var listPerson: List<Person>
    lateinit var adapter: PersonAdapterList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setView()
        setListener()
    }

    private fun setListener(){
        button.setOnClickListener {
            listPerson += Person("Juan","Luis",23)
            personDataSource.saveList(this,listPerson)
            listPerson = personDataSource.getList(this)
            adapter.setPerson(listPerson)
        }
    }

    private fun setView() {
        personDataSource= PersonFileDataSource(this,"person.txt")
        listPerson = personDataSource.getList(this)

        adapter = PersonAdapterList(this,listPerson)
        list.adapter = adapter
    }
}
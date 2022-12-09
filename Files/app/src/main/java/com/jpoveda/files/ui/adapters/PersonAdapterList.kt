package com.jpoveda.files.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.jpoveda.files.R
import com.jpoveda.files.data.persons.Person
import kotlinx.android.synthetic.main.adapter_person.view.*

class PersonAdapterList(val context: Context,val list: List<Person>): BaseAdapter() {
    var persons  = ArrayList<Person>()
    init {
        persons = list as ArrayList<Person>
    }
    override fun getCount(): Int {
        return persons.size
    }
    override fun getItem(position: Int): Any {
        return persons[position]
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getView(position: Int, p1: View?, parent: ViewGroup?): View {
        val item = this.persons[position]
        var inflator = LayoutInflater.from(context)
        var view = inflator.inflate(R.layout.adapter_person, parent, false)
        view.textView.setText(item.name)
        return view
    }
    fun setPerson(listPersons: List<Person>) {
        persons = listPersons as ArrayList<Person>
        notifyDataSetChanged()
    }
}
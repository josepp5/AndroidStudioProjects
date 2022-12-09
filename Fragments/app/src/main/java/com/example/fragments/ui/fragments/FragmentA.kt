package com.example.fragments.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragments.R
import kotlinx.android.synthetic.main.fragment_a.view.*

class FragmentA : Fragment() {

    private var name :String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Here is two ways of getting the info of the fragment
        //name = savedInstanceState?.getString("name")
        // or
        arguments.let {
             name = it?.getString("name")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_a, container, false)
        view.textView.setText(name)
        return  view
    }
}
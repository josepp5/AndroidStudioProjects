package com.jpoveda.menu2.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jpoveda.menu2.Application
import com.jpoveda.menu2.R
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.fragment_settings.view.*

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        setData(view)
        setListener(view)
        return view
    }

    private fun setListener(view: View) {
        view.btn_deletePrefs.setOnClickListener {
            Application.preferences.deletePrefs()
            setData(view)
        }
    }

    private fun setData(view: View) {
        view.et_name.setText(Application.preferences.name)
    }
}

// mirar pag 23 unidad 8, para titulo de activitis
/*
override fun onBackPressed() {
    // La clase Preferences se encarga de editar y guardar en la asignación.
    if (!et_name.text.isEmpty()) {
        Application.preferences.name = et_name.text.toString()
    }

    super.onBackPressed()
}
*/
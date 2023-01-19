package com.jpoveda.menu2.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import com.jpoveda.menu2.Application
import com.jpoveda.menu2.R
import com.jpoveda.menu2.ui.fragments.BlankFragment
import com.jpoveda.menu2.ui.fragments.FragmentA
import com.jpoveda.menu2.ui.fragments.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_settings.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configView()
        setListener()
    }

    private fun configView(){
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

    private fun setListener(){
        myNavigationView.setNavigationItemSelectedListener {
            it.isChecked = true
            when (it.itemId) {
                R.id.item11 -> {
                    // Se carga el fragment en el ViewPager2.

                    setFragment(SettingsFragment())

                    // Se cierra el Drawer Layout.
                    myDrawerLayout.close()
                    true
                }R.id.item12 -> {
                // Se carga el fragment en el ViewPager2.

                setFragment(FragmentA())

                // Se cierra el Drawer Layout.
                myDrawerLayout.close()
                true
            }R.id.item13 -> {
                // Se carga el fragment en el ViewPager2.

                setFragment(BlankFragment())

                // Se cierra el Drawer Layout.
                myDrawerLayout.close()
                true
            }
                else -> false
            }
        }
    }
    override fun onBackPressed() {
        // La clase Preferences se encarga de editar y guardar en la asignación.
        if (!et_name.text.isEmpty()) {
            Application.preferences.name = et_name.text.toString()
        }

        super.onBackPressed()
    }

    private fun setFragment(fragment : Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.myViewPager2, fragment)
        transaction.addToBackStack(null)

        transaction.commit()
    }
}
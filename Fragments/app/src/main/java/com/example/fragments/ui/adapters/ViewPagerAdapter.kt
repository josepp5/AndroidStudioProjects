package com.example.fragments.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.fragments.ui.fragments.FragmentA

class ViewPagerAdapter (supportFragmentManager: FragmentManager) :
 FragmentStatePagerAdapter(
 supportFragmentManager,
 BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
 ) {
    private val mFragmentList = ArrayList<Fragment>()

    override fun getCount(): Int {
        return mFragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    fun addFragment(fragment: Fragment) {
        mFragmentList.add(fragment)
    }

}
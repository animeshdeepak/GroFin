package com.grofin.feature.dashboard

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerFragmentAdapter(
    fragmentManager: FragmentManager,
    lifeCycle: Lifecycle,
    private val fragmentList: ArrayList<Fragment>
) : FragmentStateAdapter(fragmentManager, lifeCycle) {
    companion object {
        private const val POSITION_PAGE_1 = 0
        private const val POSITION_PAGE_2 = 1
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            POSITION_PAGE_1 -> fragmentList[position]
            POSITION_PAGE_2 -> fragmentList[position]
            else -> fragmentList[position]
        }
    }

    override fun getItemCount() = fragmentList.size
}
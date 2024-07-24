package com.example.standardcloneui.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.standardcloneui.fragment.HomeFragment
import com.example.standardcloneui.fragment.MyPageFragment
import com.example.standardcloneui.fragment.FavoriteListFragment

class ViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount() = 3
    override fun createFragment(position: Int) = when (position) {
        0 -> HomeFragment()
        1 -> FavoriteListFragment()
        2 -> MyPageFragment()
        else -> throw IllegalStateException("Invalid position: $position")
    }
}
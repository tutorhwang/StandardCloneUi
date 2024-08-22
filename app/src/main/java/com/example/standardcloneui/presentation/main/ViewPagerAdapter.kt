package com.example.standardcloneui.presentation.main

import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.standardcloneui.presentation.home.HomeFragment
import com.example.standardcloneui.presentation.mypage.MyPageFragment

class ViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount() = 2
    override fun createFragment(position: Int) = when (position) {
        0 -> HomeFragment()
        1 -> MyPageFragment()
        else -> throw IllegalStateException("Invalid position: $position")
    }
}
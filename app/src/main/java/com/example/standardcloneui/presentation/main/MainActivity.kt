package com.example.standardcloneui.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.standardcloneui.R
import com.example.standardcloneui.presentation.ListItem
import com.example.standardcloneui.databinding.ActivityMainBinding
import com.example.standardcloneui.presentation.detail.DetailFragment
import com.google.android.material.tabs.TabLayoutMediator

private const val LIFECYCLE_TAG = "MainActivity.LifeCycle"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    val tabTitles =
        listOf(R.string.title_home, R.string.title_video_list, R.string.title_my_page)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.i(LIFECYCLE_TAG, "onCreate()")
        with(binding) {
            viewPager.adapter = ViewPagerAdapter(this@MainActivity)
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = getString(tabTitles[position])
            }.attach()
        }
        supportFragmentManager.setFragmentResultListener(
            DetailFragment.KEY_RESULT,
            this
        ) { key, bundle ->
            val result = bundle.getString(key)
            Toast.makeText(this, "Fragment result: $result", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i(LIFECYCLE_TAG, "onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.i(LIFECYCLE_TAG, "onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.i(LIFECYCLE_TAG, "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.i(LIFECYCLE_TAG, "onStop()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(LIFECYCLE_TAG, "onRestart()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(LIFECYCLE_TAG, "onDestroy()")
    }

    fun showDetailFragment(item: ListItem.VideoItem) {
        val fragment = DetailFragment.newInstance(item)
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right,
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
            )
            .replace(R.id.detail_fragment_container, fragment)
            .addToBackStack(null)
            .commit()

        binding.detailFragmentContainer.visibility = View.VISIBLE
    }

    fun hideDetailFragment() {
        supportFragmentManager.popBackStack()
        binding.detailFragmentContainer.visibility = View.GONE
    }
}

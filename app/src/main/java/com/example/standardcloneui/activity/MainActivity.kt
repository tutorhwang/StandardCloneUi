package com.example.standardcloneui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.standardcloneui.R
import com.example.standardcloneui.databinding.ActivityMainBinding
import com.example.standardcloneui.fragment.HomeFragment
import com.example.standardcloneui.fragment.MyPageFragment
import com.example.standardcloneui.fragment.VideoListFragment

private const val LIFECYCLE_TAG = "MainActivity.LifeCycle"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
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
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    HomeFragment().loadFragment()
                    true
                }

                R.id.navigation_video_list -> {
                    VideoListFragment().loadFragment()
                    true
                }

                R.id.navigation_my_page -> {
                    MyPageFragment().loadFragment()
                    true
                }

                else -> false
            }
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

    private fun Fragment.loadFragment() {
        supportFragmentManager.commit {
            replace(R.id.fragment_container, this@loadFragment)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }
}

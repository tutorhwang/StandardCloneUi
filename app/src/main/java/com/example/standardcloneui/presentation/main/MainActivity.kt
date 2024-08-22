package com.example.standardcloneui.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.standardcloneui.R
import com.example.standardcloneui.presentation.ListItem
import com.example.standardcloneui.databinding.ActivityMainBinding
import com.example.standardcloneui.presentation.ai.AIResultUIState
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch

private const val LIFECYCLE_TAG = "MainActivity.LifeCycle"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<PlayerViewModel>()

    val tabTitles =
        listOf(R.string.title_home, R.string.title_video_list, R.string.title_my_page)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.motionLayout)) { v, insets ->
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

        lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->
                binding.aiDescription.text = when (uiState) {
                    is AIResultUIState.Loading -> "AI 분석중........."
                    is AIResultUIState.Success -> uiState.response.aiMessage
                    is AIResultUIState.Error -> uiState.errorMessage
                }
            }
        }

    }

    fun showMiniPlayerView(item: ListItem.VideoItem) {
        updateVideoInfo(item)
        viewModel.fetchAiAnalysisResult(item)
    }

    private fun updateVideoInfo(item: ListItem.VideoItem) {
        binding.title.text = item.title
        Glide.with(this@MainActivity).load(item.thumbnail).into(binding.thumbnail)
    }
}

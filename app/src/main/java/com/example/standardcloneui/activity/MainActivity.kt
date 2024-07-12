package com.example.standardcloneui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.standardcloneui.R
import com.example.standardcloneui.adapter.ListItem
import com.example.standardcloneui.adapter.VideoListAdapter
import com.example.standardcloneui.data.VideoList
import com.example.standardcloneui.databinding.ActivityMainBinding

private const val LIFECYCLE_TAG = "MainActivity.LifeCycle"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val videoAdapter by lazy {
        VideoListAdapter() { video ->
            val intent = Intent(applicationContext, DetailActivity::class.java).apply {
                putExtra(EXTRA_VIDEO, video)
            }
            startActivity(intent)
        }
    }

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
            setSupportActionBar(toolbar)
            val list = binding.videoList
            list.adapter = videoAdapter.apply { submitList(VideoList.list.toList()) }

            chipInsert.setOnClickListener {
                VideoList.addFirst(
                    ListItem.VideoItem(
                        "Bùm", //channel title
                        "Phân Tích Bí Ẩn Skibidi Toilet 69 Tập Full", // title
                        "https://i.ytimg.com/vi/dyuFvdd1Le4/mqdefault.jpg", //thumbnails.medium
                        "Phân Tích Bí Ẩn Skibidi Toilet 69 Tập Full --- Shopacc: https://bumroblox.net/k1 Kênh Phụ: ..." //description
                    )
                )
                videoAdapter.submitList(VideoList.list.toList()) { list.scrollToPosition(0) }
            }

            chipDelete.setOnClickListener {
                if (VideoList.list.isNotEmpty()) {
                    VideoList.removeLast()
                    videoAdapter.submitList(VideoList.list.toList()) {
                        list.scrollToPosition(0)
                    }
                }
            }

//            viewPager.adapter = ImagePagerAdapter(VideoList.list)
//            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
//                tab.text = VideoList.list[position].title
//            }.attach()
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
}
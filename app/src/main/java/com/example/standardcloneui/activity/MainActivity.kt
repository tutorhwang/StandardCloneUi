package com.example.standardcloneui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.standardcloneui.adapter.VideoListAdapter
import com.example.standardcloneui.data.Video
import com.example.standardcloneui.data.VideoList
import com.example.standardcloneui.R
import com.example.standardcloneui.databinding.ActivityMainBinding

private const val LIFECYCLE_TAG = "MainActivity.LifeCycle"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy {
        VideoListAdapter(VideoList.list)
        { video ->
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
            videoList.adapter = adapter
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add -> {
                adapter.addItem(
                    Video(
                        "Tobi Speakerfan", //channel title
                        "Detalles insanos de Skibidi Toilet 69  #skibiditoilet", // title
                        "https://i.ytimg.com/vi/rYzH1214ElA/mqdefault.jpg", //thumbnails.medium
                        "Spoiler de Skibidi Toilet 69 claro que si ............................................................................................... #skibiditoilet 69 parte 2 #memes ..." //description
                    )
                )
                binding.videoList.scrollToPosition(0)
                true
            }

            R.id.action_remove -> {
                adapter.removeItem(0)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
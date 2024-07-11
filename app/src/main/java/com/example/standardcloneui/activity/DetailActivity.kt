package com.example.standardcloneui.activity

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.standardcloneui.data.Video
import com.example.standardcloneui.databinding.ActivityDetailBinding

const val EXTRA_VIDEO = "EXTRA_VIDEO"

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_VIDEO, Video::class.java)
        } else {
            intent.getParcelableExtra(EXTRA_VIDEO)
        }?.also {
            Glide.with(this).load(it.thumbnail).into(binding.picture)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // 뒤로 가기 버튼 클릭 시
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
package com.example.standardcloneui.activity

import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.standardclonui.R

import com.example.standardcloneui.data.Video
import com.example.standardcloneui.data.VideoList

const val EXTRA_VIDEO = "EXTRA_VIDEO"

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val video = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_VIDEO, Video::class.java)
        } else {
            intent.getParcelableExtra(EXTRA_VIDEO)
        }

        video?.also { v ->
            findViewById<ImageView>(R.id.picture)?.apply {
                Glide.with(this@apply).load(v.thumbnail).into(this@apply)
            }
        }

        findViewById<ImageView>(R.id.selected_image)?.setImageURI(VideoList.imageUri)
    }
}
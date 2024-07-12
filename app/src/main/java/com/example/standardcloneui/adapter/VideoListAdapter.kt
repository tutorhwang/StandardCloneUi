package com.example.standardcloneui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.standardcloneui.data.Video
import com.example.standardcloneui.R
import com.example.standardcloneui.databinding.ItemLayoutBinding

class VideoListAdapter(private val onClick: (Video) -> Unit) :
    ListAdapter<Video, VideoListAdapter.VideoItemHolder>(object : DiffUtil.ItemCallback<Video>() {
        override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean {
            return oldItem == newItem
        }
    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoItemHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoItemHolder(binding)
    }

    override fun onBindViewHolder(itemHolder: VideoItemHolder, position: Int) {
        runCatching {
            itemHolder.bind(getItem(position))
            itemHolder.positionView.text = position.toString()
        }.onFailure { exception ->
            Log.e("VideoListAdapter", "Exception! ${exception.message}")
        }
    }

    inner class VideoItemHolder(binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val channelLogoView = binding.logo
        private val channelNameView = binding.channelName
        private val titleView = binding.title
        private val thumbnailView = binding.mainImage
        val positionView = binding.position

        fun bind(video: Video) {
            with(video) {
                Log.d("RecyclerView", "bind $video")
                Glide.with(itemView).load(thumbnail).into(thumbnailView)
                thumbnailView.setOnClickListener { onClick(this) }
                titleView.text = title
                channelNameView.text = channelTitle
                channelLogoView.setImageResource(R.drawable.haelin)
            }
        }
    }
}
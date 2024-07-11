package com.example.standardcloneui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.standardcloneui.data.Video
import com.example.standardcloneui.data.VideoList
import com.example.standardcloneui.R
import com.example.standardcloneui.databinding.ItemLayoutBinding

class VideoListAdapter(private val list: List<Video>, private val onClick: (Video) -> Unit) :
    RecyclerView.Adapter<VideoListAdapter.VideoItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoItemHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoItemHolder(binding)
    }

    override fun onBindViewHolder(itemHolder: VideoItemHolder, position: Int) {
        runCatching {
            itemHolder.bind(list[position])
        }.onFailure { exception ->
            Log.e("VideoListAdapter", "Exception! ${exception.message}")
        }
    }

    override fun getItemCount() = list.size

    inner class VideoItemHolder(binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val channelLogoView = binding.logo
        private val channelNameView = binding.channelName
        private val titleView = binding.title
        private val thumbnailView = binding.mainImage

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

    // 데이터 추가
    fun addItem(video: Video) {
        VideoList.addFirst(video)
        notifyItemInserted(0)
    }

    // 데이터 삭제
    fun removeItem(position: Int) {
        if (position < list.size) {
            VideoList.removeAt(position)
            notifyItemRemoved(position)
        }
    }
}
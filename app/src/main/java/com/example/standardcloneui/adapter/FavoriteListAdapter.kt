package com.example.standardcloneui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.standardcloneui.R
import com.example.standardcloneui.data.ListItem
import com.example.standardcloneui.databinding.ItemLayoutBinding

class FavoriteListAdapter(private val onClick: (ListItem.VideoItem) -> Unit) :
    ListAdapter<ListItem.VideoItem, FavoriteListAdapter.VideoItemHolder>(object :
        DiffUtil.ItemCallback<ListItem.VideoItem>() {
        override fun areItemsTheSame(
            oldItem: ListItem.VideoItem,
            newItem: ListItem.VideoItem
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: ListItem.VideoItem,
            newItem: ListItem.VideoItem
        ): Boolean {
            return oldItem == newItem
        }
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoItemHolder {
        val binding =
            ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoItemHolder(binding)
    }

    override fun onBindViewHolder(itemHolder: VideoItemHolder, position: Int) {
        runCatching {
            itemHolder.bind(getItem(position))
        }.onFailure { exception ->
            Log.e("FavoriteListAdapter", "Exception! ${exception.message}")
        }
    }

    inner class VideoItemHolder(binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val channelLogoView = binding.logo
        private val channelNameView = binding.channelName
        private val titleView = binding.title
        private val thumbnailView = binding.mainImage

        fun bind(video: ListItem.VideoItem) {
            with(video) {
                Glide.with(itemView).load(thumbnail).into(thumbnailView)
                titleView.text = title
                channelNameView.text = channelTitle
                channelLogoView.setImageResource(R.drawable.haelin)
                itemView.setOnClickListener { onClick(video) }
            }
        }
    }
}
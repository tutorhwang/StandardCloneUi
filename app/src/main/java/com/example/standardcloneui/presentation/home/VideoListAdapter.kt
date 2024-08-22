package com.example.standardcloneui.presentation.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.standardcloneui.R
import com.example.standardcloneui.presentation.ListItem
import com.example.standardcloneui.databinding.ItemLayoutBinding
import com.example.standardcloneui.databinding.ItemLayoutHeaderBinding

class VideoListAdapter(
    private val onClick: (ListItem) -> Unit,
    private val onLongClick: (ListItem) -> Unit
) :
    ListAdapter<ListItem, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<ListItem>() {
        override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return when {
                oldItem is ListItem.HeaderItem && newItem is ListItem.HeaderItem ->
                    oldItem.thumbnail == newItem.thumbnail

                oldItem is ListItem.VideoItem && newItem is ListItem.VideoItem ->
                    oldItem.title == newItem.title

                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return oldItem == newItem
        }
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> {
                val binding =
                    ItemLayoutHeaderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                HeaderItemHolder(binding)
            }

            else -> {
                val binding =
                    ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                VideoItemHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(itemHolder: RecyclerView.ViewHolder, position: Int) {
        runCatching {
            when (val item = getItem(position)) {
                is ListItem.HeaderItem -> {
                    (itemHolder as HeaderItemHolder).bind(item)
                }

                is ListItem.VideoItem -> {
                    (itemHolder as VideoItemHolder).bind(item)
                }
            }
        }.onFailure { exception ->
            Log.e("VideoListAdapter", "Exception! ${exception.message}")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is ListItem.HeaderItem -> TYPE_HEADER
            is ListItem.VideoItem -> TYPE_CONTENT
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
                thumbnailView.setOnClickListener { onClick(this) }
                thumbnailView.setOnLongClickListener {
                    onLongClick(this)
                    true
                }
            }
        }
    }

    inner class HeaderItemHolder(binding: ItemLayoutHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val thumbnailView = binding.mainImage

        fun bind(item: ListItem.HeaderItem) {
            with(item) {
                Glide.with(itemView).load(thumbnail).into(thumbnailView)
            }
        }
    }

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_CONTENT = 1
    }
}
package com.example.standardcloneui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.standardcloneui.activity.MainActivity
import com.example.standardcloneui.data.ListItem
import com.example.standardcloneui.adapter.VideoListAdapter
import com.example.standardcloneui.data.VideoList
import com.example.standardcloneui.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val videoAdapter by lazy {
        VideoListAdapter { video ->
            if (video !is ListItem.VideoItem) return@VideoListAdapter
            (activity as? MainActivity)?.showDetailFragment(video)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            recyclerView.adapter = videoAdapter.apply { submitList(VideoList.list.toList()) }
            chipAdd.setOnClickListener {
                VideoList.addFirst(
                    ListItem.VideoItem(
                        "Bùm", //channel title
                        "Phân Tích Bí Ẩn Skibidi Toilet 69 Tập Full", // title
                        "https://i.ytimg.com/vi/dyuFvdd1Le4/mqdefault.jpg", //thumbnails.medium
                        "Phân Tích Bí Ẩn Skibidi Toilet 69 Tập Full --- Shopacc: https://bumroblox.net/k1 Kênh Phụ: ..." //description
                    )
                )
                videoAdapter.submitList(VideoList.list.toList()) { recyclerView.scrollToPosition(0) }
            }

            chipRemove.setOnClickListener {
                if (VideoList.list.isNotEmpty()) {
                    VideoList.removeLast()
                    videoAdapter.submitList(VideoList.list.toList()) {
                        recyclerView.scrollToPosition(0)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
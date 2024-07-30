package com.example.standardcloneui.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.standardcloneui.presentation.main.MainActivity
import com.example.standardcloneui.presentation.ListItem
import com.example.standardcloneui.presentation.VideoList
import com.example.standardcloneui.databinding.FragmentHomeBinding
import com.example.standardcloneui.presentation.detail.DetailFragment

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
        parentFragmentManager.setFragmentResultListener(DetailFragment.KEY_FRAGMENT_RESULT, viewLifecycleOwner) { key, bundle ->
            val result = bundle.getString(DetailFragment.KEY_FRAGMENT_RESULT)
            Toast.makeText(requireContext(), "Fragment result: $result", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
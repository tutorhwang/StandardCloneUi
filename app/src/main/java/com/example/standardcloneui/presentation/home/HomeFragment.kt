package com.example.standardcloneui.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.standardcloneui.presentation.main.MainActivity
import com.example.standardcloneui.presentation.ListItem
import com.example.standardcloneui.databinding.FragmentHomeBinding
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()

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
        initView()
        initViewModel()
    }

    private fun initView() = with(binding) {
        recyclerView.adapter = videoAdapter
        chipKorea.setOnClickListener {
            viewModel.fetchTrendingVideos("KR")
        }

        chipUs.setOnClickListener {
            viewModel.fetchTrendingVideos("US")
        }
    }

    private fun initViewModel() = with(viewModel) {
        trendingVideos.observe(viewLifecycleOwner) {
            videoAdapter.submitList(it)
        }
        fetchTrendingVideos("US")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
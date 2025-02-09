package com.example.standardcloneui.presentation.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.standardcloneui.databinding.FragmentVideoListBinding
import com.example.standardcloneui.presentation.main.FavoriteViewModel
import com.example.standardcloneui.presentation.main.FavoriteViewModelFactory

class FavoriteListFragment : Fragment() {

    private var _binding: FragmentVideoListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavoriteViewModel by activityViewModels {
        FavoriteViewModelFactory(requireContext())
    }
    private val adapter by lazy { FavoriteListAdapter { viewModel.removeFavoriteItem(it) } }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        _binding = FragmentVideoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }

    private fun initView() = with(binding) {
        recyclerView.adapter = adapter
    }

    private fun initViewModel() = with(viewModel) {
        favoriteList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
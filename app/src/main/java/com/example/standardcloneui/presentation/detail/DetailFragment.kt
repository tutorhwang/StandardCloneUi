package com.example.standardcloneui.presentation.detail

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.standardcloneui.databinding.FragmentDetailBinding
import com.example.standardcloneui.presentation.ListItem
import com.example.standardcloneui.presentation.main.MainActivity
import com.example.standardcloneui.presentation.main.FavoriteViewModel
import com.example.standardcloneui.presentation.main.FavoriteViewModelFactory

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoriteViewModel by activityViewModels {
        FavoriteViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val item: ListItem.VideoItem? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(ARG_VIDEO, ListItem.VideoItem::class.java)
        } else {
            arguments?.getParcelable(ARG_VIDEO)
        }

        item?.let {
            Glide.with(this).load(it.thumbnail).into(binding.picture)
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    (activity as? MainActivity)?.hideDetailFragment()
                }
            })

        binding.picture.setOnClickListener {
            item?.let { viewModel.addFavoriteItem(it) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_VIDEO = "ARG_VIDEO"

        @JvmStatic
        fun newInstance(video: ListItem.VideoItem) = DetailFragment().apply {
            arguments = bundleOf(ARG_VIDEO to video)
        }
    }
}
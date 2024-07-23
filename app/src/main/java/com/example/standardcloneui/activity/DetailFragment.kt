package com.example.standardcloneui.activity

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.standardcloneui.data.ListItem
import com.example.standardcloneui.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
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

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                (activity as? MainActivity)?.hideDetailFragment()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_VIDEO = "ARG_VIDEO"

        @JvmStatic
        fun newInstance(video: ListItem.VideoItem) = DetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_VIDEO, video)
            }
        }
    }
}
package com.example.standardcloneui.presentation.mypage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.standardcloneui.presentation.favorite.FavoriteListAdapter
import com.example.standardcloneui.databinding.FragmentMyPageBinding
import com.example.standardcloneui.presentation.main.FavoriteViewModel
import com.example.standardcloneui.presentation.main.FavoriteViewModelFactory

class MyPageFragment : Fragment() {
    private var _binding: FragmentMyPageBinding? = null
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
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.thumbnail.setOnClickListener { openGalleryForImage() }
        binding.recyclerView.adapter = adapter
        initViewModel()
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

    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            uri?.also { imageUri ->
                binding.thumbnail.setImageURI(imageUri)
                requireActivity().contentResolver.takePersistableUriPermission(
                    imageUri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
            }
        }

    private fun openGalleryForImage() {
        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }
}
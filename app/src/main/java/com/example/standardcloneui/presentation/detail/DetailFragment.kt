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
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.standardcloneui.data.model.ai.Message
import com.example.standardcloneui.data.model.ai.OpenAIRequest
import com.example.standardcloneui.data.repository.AiRepository
import com.example.standardcloneui.databinding.FragmentDetailBinding
import com.example.standardcloneui.presentation.ListItem
import com.example.standardcloneui.presentation.main.MainActivity
import com.example.standardcloneui.presentation.main.FavoriteViewModel
import com.example.standardcloneui.presentation.main.FavoriteViewModelFactory
import kotlinx.coroutines.launch

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoriteViewModel by activityViewModels {
        FavoriteViewModelFactory(requireContext())
    }
    private val aiRepository: AiRepository by lazy { AiRepository() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val item: ListItem.VideoItem = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(ARG_VIDEO, ListItem.VideoItem::class.java)
        } else {
            arguments?.getParcelable(ARG_VIDEO)
        } ?: return

        item.let {
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
            item.let { viewModel.addFavoriteItem(it) }
        }

        lifecycleScope.launch {
            val request = OpenAIRequest(
                messages = listOf(
                    Message(
                        "user",
                        "제목: ${item.title}, 채널: ${item.channelTitle}, 설명: ${item.description}"
                    ),
                    Message(
                        "system",
                        "너는 단호하고 재미있는 유투브 컨텐츠 분석가야. " +
                                "유투브 비디오 title과 채널명, 설명을 분석해서 재미있을 것 같은지 " +
                                "추천여부 멘트를 재미있게 작성해줘."
                    )
                )
            )
            val result = aiRepository.createChatCompletion(request)
            binding.aiDescription.text = result.choices?.get(0)?.message?.content ?: "결과 없음"
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
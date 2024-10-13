package com.example.newsjam_android.ui.view.account

import androidx.navigation.fragment.findNavController
import com.example.newsjam_android.R
import com.example.newsjam_android.data.model.TagData
import com.example.newsjam_android.databinding.FragmentLikeChoiceBinding
import com.example.newsjam_android.ui.base.BaseFragment
import com.example.newsjam_android.ui.view.adapter.LikeChoiceAdapter
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager

class LikeChoiceFragment : BaseFragment<FragmentLikeChoiceBinding>(R.layout.fragment_like_choice) {

    private val likeChoiceAdapter = LikeChoiceAdapter()
    override fun setLayout() {
        setRecyclerView()
        loadTags()
        setOnClickView()
    }

    private fun setOnClickView() {
        binding.appCompatButton.setOnClickListener {
            findNavController().navigate(R.id.action_likeChoiceFragment_to_hotNewsConfirmFragment)
        }
    }

    private fun loadTags() {
        val tags = listOf(
            "정치", "경제", "사회", "생활/문화", "IT/과학", "금융", "재테크", "부동산",
            "연예", "영화", "예술", "TV", "엔터테인먼트", "여행", "야구", "해외야구",
            "축구", "해외축구", "농구", "배구", "골프", "e스포츠"
        ).map { name -> TagData(isSelected = false, title = name) }

        likeChoiceAdapter.submitList(tags)
    }

    private fun setRecyclerView() {
        binding.fragmentLikeFlexBoxRv.apply {
            layoutManager = FlexboxLayoutManager(requireActivity()).apply {
                flexWrap = FlexWrap.WRAP
                flexDirection = FlexDirection.ROW
            }
            adapter = likeChoiceAdapter
        }
    }
}
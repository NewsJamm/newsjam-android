package com.example.newsjam_android.ui.view.account

import android.content.Intent
import com.example.newsjam_android.R
import com.example.newsjam_android.data.model.TagData
import com.example.newsjam_android.databinding.FragmentLikeChoiceBinding
import com.example.newsjam_android.databinding.HotNewsConfirmFragmentBinding
import com.example.newsjam_android.ui.base.BaseFragment
import com.example.newsjam_android.ui.view.adapter.LikeChoiceAdapter
import com.example.newsjam_android.ui.view.home.MainActivity
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager

class HotNewsConfirmFragment : BaseFragment<HotNewsConfirmFragmentBinding>(R.layout.hot_news_confirm_fragment) {

    override fun setLayout() {
        setOnClickView()
    }

    private fun setOnClickView() {
        binding.appCompatButton.setOnClickListener {
            startActivity(Intent(requireActivity(), MainActivity::class.java))
            requireActivity().finish()
        }
    }


}
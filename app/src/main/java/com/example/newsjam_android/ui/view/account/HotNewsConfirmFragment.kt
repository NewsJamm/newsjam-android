package com.example.newsjam_android.ui.view.account

import android.content.Intent
import android.view.View
import com.example.newsjam_android.R
import com.example.newsjam_android.databinding.HotNewsConfirmFragmentBinding
import com.example.newsjam_android.ui.base.BaseFragment
import com.example.newsjam_android.ui.view.home.MainActivity

class HotNewsConfirmFragment :
    BaseFragment<HotNewsConfirmFragmentBinding>(R.layout.hot_news_confirm_fragment) {

    override fun setLayout() {
        setOnClickView()
    }

    private fun setOnClickView() {
        if (arguments?.getString("page") == "my_page"){
            binding.appCompatButton.visibility = View.GONE
        }
        binding.appCompatButton.setOnClickListener {
            startActivity(Intent(requireActivity(), MainActivity::class.java))
            requireActivity().finish()
        }
    }


}
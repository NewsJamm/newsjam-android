package com.example.newsjam_android.ui.view.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.newsjam_android.R
import com.example.newsjam_android.databinding.FragmentLoginBinding
import com.example.newsjam_android.ui.base.BaseFragment

class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    override fun setLayout() {
        setOnClickView()
    }

    private fun setOnClickView() {
        binding.activityLoginSocial1Bt.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_likeChoiceFragment)
        }
    }
}
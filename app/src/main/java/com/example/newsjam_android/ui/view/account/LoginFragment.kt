package com.example.newsjam_android.ui.view.account

import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.newsjam_android.GlobalApplication
import com.example.newsjam_android.R
import com.example.newsjam_android.databinding.FragmentLoginBinding
import com.example.newsjam_android.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    override fun setLayout() {
        setOnClickView()
    }

    private fun setOnClickView() {
        binding.activityLoginSocial1Bt.setOnClickListener {
            lifecycleScope.launch {
                val token = GlobalApplication.instance.tokenManager.getFireBaseToken.first()
                Log.d("토큰 보기", "$token")
                findNavController().navigate(R.id.action_loginFragment_to_likeChoiceFragment)
                //토큰 서버로 전달
            }
        }

        binding.activityLoginSocial2Bt.setOnClickListener {
            lifecycleScope.launch {
                val token = GlobalApplication.instance.tokenManager.getFireBaseToken.first()
                Log.d("토큰 보기", "$token")
                findNavController().navigate(R.id.action_loginFragment_to_loginEmailFragment)
                //토큰 서버로 전달
            }
        }
    }
}
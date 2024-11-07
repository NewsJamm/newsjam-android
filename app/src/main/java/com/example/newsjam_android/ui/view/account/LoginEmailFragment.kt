package com.example.newsjam_android.ui.view.account

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.data.request.SignInDTO
import com.example.newsjam_android.R
import com.example.newsjam_android.databinding.FragmentLoginEmailBinding
import com.example.newsjam_android.ui.base.BaseFragment
import com.example.newsjam_android.ui.view.home.MainActivity
import com.example.newsjam_android.ui.view.viewmodel.AccountViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginEmailFragment : BaseFragment<FragmentLoginEmailBinding>(R.layout.fragment_login_email) {

    private val accountViewModel : AccountViewModel by viewModels()
    private val signInDTO = SignInDTO("","")

    override fun setLayout() {
        setOnClickedButton()
        buttonState()
        observeLifeCycle()
    }

    private fun setOnClickedButton() {
        binding.fragmentLoginEmailNextBt.tag = resources.getString(R.string.next)
        binding.fragmentLoginEmailNextBt.setOnClickListener {
            nextStep()
        }
    }

    private fun nextStep() {
        if (binding.fragmentLoginEmailNextBt.tag == resources.getString(R.string.next) && binding.fragmentLoginEmailEt.text.isNotBlank()) {
            signInDTO.name = binding.fragmentLoginEmailEt.text.toString()
            binding.fragmentLoginEmailEt.text.clear()
            binding.fragmentLoginEmailNextBt.tag = resources.getString(R.string.last)
            binding.fragmentLoginEmailTitleTv.text = "이메일을 입력해주세요."
        } else {
            // EditText의 내용이 있을 때만 다음 화면으로 넘어갑니다.
            if (binding.fragmentLoginEmailEt.text.isNotBlank()) {
                signInDTO.loginId = binding.fragmentLoginEmailEt.text.toString()
                accountViewModel.postSignIn(signInDTO)

            }
        }
    }

    private fun buttonState() {
        binding.fragmentLoginEmailEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val isNotEmpty = binding.fragmentLoginEmailEt.text.isNotBlank()
                binding.fragmentLoginEmailNextBt.setBackgroundResource(
                    if (isNotEmpty) R.drawable.shape_fill_red_square_rounded_8dp
                    else R.drawable.shape_fill_f4f4f4_square_rounded_8dp
                )
            }
        })
    }

    private fun observeLifeCycle() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                accountViewModel.isLoginSuccessful.collectLatest { isSuccessful ->
                    if (isSuccessful) {
                        startActivity(Intent(requireActivity(), MainActivity::class.java))
                        requireActivity().finish()
                    }
                }
            }
        }
    }

}

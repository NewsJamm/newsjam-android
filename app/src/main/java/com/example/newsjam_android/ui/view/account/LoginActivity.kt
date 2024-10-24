package com.example.newsjam_android.ui.view.account

import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.newsjam_android.R
import com.example.newsjam_android.databinding.ActivityLoginBinding
import com.example.newsjam_android.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private lateinit var navController: NavController
    override fun setLayout() {
        val host = supportFragmentManager.findFragmentById(binding.activityLoginContainerView.id) as NavHostFragment
        navController = host.navController
    }

}
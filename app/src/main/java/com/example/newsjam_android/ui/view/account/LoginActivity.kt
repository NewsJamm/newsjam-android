package com.example.newsjam_android.ui.view.account

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.database.User
import com.example.newsjam_android.R
import com.example.newsjam_android.databinding.ActivityLoginBinding
import com.example.newsjam_android.ui.base.BaseActivity
import com.example.newsjam_android.ui.view.viewmodel.AccountViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private lateinit var accountViewModel : AccountViewModel

    private lateinit var navController: NavController
    override fun setLayout() {
        val user = User("dd")
        Log.d("모듈 연결 확인",user.id)
        val host = supportFragmentManager.findFragmentById(binding.activityLoginContainerView.id) as NavHostFragment
        navController = host.navController
        accountViewModel = ViewModelProvider(this)[AccountViewModel::class.java]
    }

}
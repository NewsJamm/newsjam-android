package com.example.newsjam_android.ui.view.news

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.newsjam_android.R
import com.example.newsjam_android.databinding.FragmentNewsPickBinding
import com.example.newsjam_android.ui.base.BaseFragment
import com.example.newsjam_android.ui.view.adapter.News2Adapter
import com.example.newsjam_android.ui.view.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsPickFragment :
    BaseFragment<FragmentNewsPickBinding>(R.layout.fragment_news_pick) {
    val mainViewModel: MainViewModel by activityViewModels()
    lateinit var categoryAdapter: News2Adapter
    override fun setLayout() {
        initRecyclerView()
        observeLifeCycle()
    }

    private fun initRecyclerView() {
        categoryAdapter = News2Adapter()
        mainViewModel.getRealTimeNewsPick("1", "5")
        binding.fragmentNewsCategoryRv.adapter = categoryAdapter
    }

    private fun observeLifeCycle() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                mainViewModel.newsPick.collectLatest {
                    binding.fragmentNewsCategoryRv.adapter = categoryAdapter
                    categoryAdapter.submitList(it.result?.second)
                    categoryAdapter.setList(it.result?.first)
                }
            }
        }
    }


}
package com.example.newsjam_android.ui.view.mypage

import com.example.newsjam_android.R
import com.example.data.model.RankType
import com.example.newsjam_android.databinding.FragmentRecentBinding
import com.example.newsjam_android.ui.base.BaseFragment
import com.example.newsjam_android.ui.view.adapter.RecentAdapter
import com.example.newsjam_android.ui.view.listener.AdapterItemClickedListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecentFragment : BaseFragment<FragmentRecentBinding>(R.layout.fragment_recent),
    AdapterItemClickedListener {

    private lateinit var recentAdapter: RecentAdapter
    override fun setLayout() {
        initAdapter()
    }

    private fun initAdapter() {
        recentAdapter = RecentAdapter(this)
        binding.fragmentScrapRv.adapter = recentAdapter
        recentAdapter.submitList(
            listOf(
                RankType.SearchResultType(),
                RankType.SearchResultType()
            )
        )
    }

    override fun onClick(item: Any) {
        
    }


}
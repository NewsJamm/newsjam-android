package com.example.newsjam_android.ui.view.mypage

import com.example.newsjam_android.R
import com.example.data.model.ScrapType
import com.example.newsjam_android.databinding.FragmentScrapBinding
import com.example.newsjam_android.ui.base.BaseFragment
import com.example.newsjam_android.ui.view.adapter.ScrapAdapter
import com.example.newsjam_android.ui.view.listener.AdapterItemClickedListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScrapFragment : BaseFragment<FragmentScrapBinding>(R.layout.fragment_scrap),
    AdapterItemClickedListener {

    private lateinit var scrapAdapter: ScrapAdapter
    override fun setLayout() {
        initAdapter()
    }

    private fun initAdapter() {
        scrapAdapter = ScrapAdapter(this)
        binding.fragmentScrapRv.adapter = scrapAdapter
        scrapAdapter.submitList(
            listOf(
                ScrapType(isScrap = true),
                ScrapType()
            )
        )
    }

    override fun onClick(item: Any) {

    }


}
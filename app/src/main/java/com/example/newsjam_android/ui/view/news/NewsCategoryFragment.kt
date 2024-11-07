package com.example.newsjam_android.ui.view.news

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.data.model.NewsItem
import com.example.domain.mapper.asNewsItem
import com.example.newsjam_android.R
import com.example.newsjam_android.data.enums.OrderListType
import com.example.newsjam_android.databinding.FragmentNewsCategoryBinding
import com.example.newsjam_android.ui.base.BaseFragment
import com.example.newsjam_android.ui.view.adapter.News1Adapter
import com.example.newsjam_android.ui.view.listener.AdapterItemClickedListener
import com.example.newsjam_android.ui.view.viewmodel.CategoryViewModel
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsCategoryFragment(
    private val tag: String
) :
    BaseFragment<FragmentNewsCategoryBinding>(R.layout.fragment_news_category),
    AdapterItemClickedListener {
    private lateinit var categoryAdapter: News1Adapter
    private val categoryViewModel: CategoryViewModel by viewModels()

    companion object {
        var PAGE = 1
        const val CATEGORYSIZE = 10
    }

    override fun onStart() {
        super.onStart()
        PAGE = 1
        sortRecyclerList(OrderListType.LATEST)
    }

    override fun setLayout() {
        initRecyclerView()
        initTab()
        observeLifeCycle()
    }

    private fun initRecyclerView() {
        categoryAdapter = News1Adapter(this)
        binding.fragmentNewsCategoryRv.adapter = categoryAdapter
    }


    private fun callCategoryWithSort(orderListType: OrderListType) {
        categoryViewModel.getCategory(tag, orderListType.name, "$PAGE", "$CATEGORYSIZE")
    }

    private fun sortRecyclerList(ot: OrderListType) {
        PAGE = 1
        when (ot) {
            OrderListType.LATEST -> callCategoryWithSort(OrderListType.LATEST)
            OrderListType.POPULAR -> callCategoryWithSort(OrderListType.POPULAR)
        }
    }

    private fun observeLifeCycle() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                categoryViewModel.category.collectLatest { response ->
                    if (response.isSuccess) {
                        response.let {
                            Log.d("카테고리리스트", "Data Loaded: ${it.result?.newsList}")
                            val newsList = it.result?.newsList?.map { item -> item.asNewsItem() }
                            categoryAdapter.submitList(newsList) {
                                binding.fragmentNewsCategoryRv.scrollToPosition(0)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun initTab() {
        with(binding.fragmentNewsCategoryTb) {
            addTab(
                newTab().setText("최신순")
            )
            addTab(
                newTab().setText("조회순")
            )
            addOnTabSelectedListener(object :
                TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when (tab?.text.toString()) {
                        "최신순" -> {
                            sortRecyclerList(OrderListType.LATEST)
                            Log.d("카테고리리스트", "Data 클릭라테")

                        }

                        "조회순" -> {
                            sortRecyclerList(OrderListType.POPULAR)
                            Log.d("카테고리리스트", "Data 클릭파풀")

                        }
                    }
                    binding.fragmentNewsCategoryRv.smoothScrollToPosition(0)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(p0: TabLayout.Tab?) {
                }

            })
        }
    }

    override fun onClick(item: Any) {
        item as NewsItem
        findNavController().navigate(R.id.action_newsFragment_to_newsDescriptionFragment,
            Bundle().apply {
                putSerializable("NewsItem", item)
            }
        )

    }


}
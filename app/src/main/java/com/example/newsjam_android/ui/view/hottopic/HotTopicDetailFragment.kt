package com.example.newsjam_android.ui.view.hottopic

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.CategoryValueDataEntry
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.chart.common.listener.Event
import com.anychart.chart.common.listener.ListenersInterface
import com.anychart.charts.TagCloud
import com.anychart.scales.OrdinalColor
import com.example.data.model.NewsItem
import com.example.domain.mapper.asNewsItem
import com.example.newsjam_android.R
import com.example.newsjam_android.databinding.FragmentHotTopicDetailBinding
import com.example.newsjam_android.ui.base.BaseFragment
import com.example.newsjam_android.ui.view.adapter.News1Adapter
import com.example.newsjam_android.ui.view.adapter.News2Adapter
import com.example.newsjam_android.ui.view.listener.AdapterItemClickedListener
import com.example.newsjam_android.ui.view.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HotTopicDetailFragment(
    private val tag: String
) : BaseFragment<FragmentHotTopicDetailBinding>(R.layout.fragment_hot_topic_detail),
    AdapterItemClickedListener {
    val data: MutableList<DataEntry> = mutableListOf(ValueDataEntry("", 0))
    val mainViewModel: MainViewModel by viewModels()
    private lateinit var tagCloud: TagCloud
    lateinit var news1Adapter: News1Adapter
    lateinit var news2Adapter: News2Adapter
    val wordList : MutableList<NewsItem> = mutableListOf()
    companion object {
        var PAGE = 1
        const val PAGESIZE = 5
    }

    override fun onStart() {
        super.onStart()
        PAGE = 1
    }

    override fun setLayout() {
        initCloud()
        initAdapter()
        observeLifeCycle()
        setOnClick()
    }

    private fun setOnClick() {
        binding.fragmentHotTopicDetailAddBt.setOnClickListener {
            callKeyWordNewsData()
        }
    }

    private fun initAdapter() {
        news1Adapter = News1Adapter(this)
        news2Adapter = News2Adapter()
        mainViewModel.getRealTimeNewsPick("1", "5")
        with(binding) {
            fragmentHotTopicDetailRv.adapter = news1Adapter
            fragmentHotTopicDetailRv2.adapter = news2Adapter
        }
    }

    private fun callKeyWordNewsData() {
        mainViewModel.getKeyHotTopicKeyWord(tag, "${PAGE++}", "$PAGESIZE")
    }

    private fun observeLifeCycle() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                mainViewModel.newsPick.collectLatest {
                    news2Adapter.submitList(it.result?.second)
                    news2Adapter.setList(it.result?.first)
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                mainViewModel.keyWord.collectLatest { response ->
                    if(response.isSuccess) {
                        if (response.result!!.isLast) {
                            Toast.makeText(requireContext(), "마지막 뉴스 입니다.", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            val list = response.result?.newsList?.map { it.asNewsItem() }
                            Log.d("리스트리스", "$list")
                            if (list != null) {
                                wordList.addAll(list)
                            }
                            news1Adapter.submitList(wordList.toList())
                        }
                    }
                }
            }
        }
    }


    private fun initCloud() {
        callKeyWordNewsData()
        binding.fragmentHotTopicDetailTv.text = "$tag 연간 키워드"
        tagCloud = AnyChart.tagCloud()
        //데이터 추가
        val data: MutableList<DataEntry> = ArrayList()
        data.add(CategoryValueDataEntry("김준하", "백엔드", 1383220000))
        data.add(CategoryValueDataEntry("김진하", "백엔드", 1316000000))
        data.add(CategoryValueDataEntry("박지원", "안드로이드", 324982000))
        data.add(CategoryValueDataEntry("이은규", "PM", 324982000))

        val ordinalColor = OrdinalColor.instantiate().apply {
            colors(arrayOf("#7F21F7", "#AD71FA", "#B47EFA", "#D2B0FF"))
        }
        with(tagCloud) {
            colorScale(ordinalColor)
            angles(arrayOf(-90.0, 0.0, 90.0))
            colorRange().enabled(true)
            colorRange().colorLineSize(15.0)
            data(data);
            setOnClickListener(object :
                ListenersInterface.OnClickListener(arrayOf("x", "category")) {
                override fun onClick(event: Event) {
                    val clickedTag = event.data["x"]  // 클릭한 단어 (x)
                    val category = event.data["category"]  // 카테고리 (category)
                    Toast.makeText(requireContext(), "$clickedTag", Toast.LENGTH_SHORT).show()
                    // 원하는 동작 수행
                    Log.d("로그", "$clickedTag, Category: $category")
                }
            })
        }
        binding.fragmentHotTopicDetailAcv.setChart(tagCloud);
    }

    override fun onClick(item: Any) {
        findNavController().navigate(R.id.action_hotTopicFragment_to_newsDescriptionFragment)
    }


}
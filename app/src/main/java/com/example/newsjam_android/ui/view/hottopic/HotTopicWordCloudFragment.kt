package com.example.newsjam_android.ui.view.hottopic

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.chart.common.listener.Event
import com.anychart.chart.common.listener.ListenersInterface
import com.anychart.charts.TagCloud
import com.anychart.scales.OrdinalColor
import com.example.newsjam_android.R
import com.example.newsjam_android.databinding.FragmentHotTopicWordCloudBinding
import com.example.newsjam_android.ui.base.BaseFragment
import com.example.newsjam_android.ui.view.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HotTopicWordCloudFragment(private val title: String) :
    BaseFragment<FragmentHotTopicWordCloudBinding>(R.layout.fragment_hot_topic_word_cloud) {
    val data: MutableList<DataEntry> = mutableListOf(ValueDataEntry("", 0))
    private lateinit var tagCloud: TagCloud
    private val mainViewModel: MainViewModel by viewModels()
    override fun setLayout() {
        initCloud()
        getHotTopic()
        observeLifeCycle()
        setChartClickListener()
    }

    private fun observeLifeCycle() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                mainViewModel.hotTopic.collectLatest {
                    Log.d("시작1", it.toString())
                    when (it.isSuccess) {
                        true -> {
                            updateChartWithData()
                        }
                        false -> {
                            Log.d("시작3", it.toString())
                        }
                    }
                }
            }
        }
    }

    private fun updateChartWithData() {
        data.clear() // 기존 데이터 클리어
        mainViewModel.hotTopic.value.result?.word_list?.forEach { topic ->
            val entry = ValueDataEntry(topic.word, topic.count.toInt())
            data.add(entry)
            Log.d("차트 데이터", "Word: ${topic.word}, Count: ${topic.count}") // 각 데이터 항목 로깅
        }
        val ordinalColor = OrdinalColor.instantiate().apply {
            colors(arrayOf("#7F21F7", "#AD71FA", "#B47EFA", "#D2B0FF"))
        }
        with(tagCloud) {
            colorScale(ordinalColor)
            angles(arrayOf(-90.0, 0.0, 90.0))
            colorRange().enabled(true)
            colorRange().colorLineSize(15.0)
            data(data) // 차트에 데이터 업데이트
            draw(false) // 차트를 동기적으로 다시 그림
        }
        binding.fragmentHotTopicWordCloudWc.setChart(tagCloud);
    }

    private fun getHotTopic() {
        mainViewModel.getHotTopic(HOTTOPICWORDSCOUNT)
    }


    private fun initCloud() {
        tagCloud = AnyChart.tagCloud()
        //데이터 추가
        val ordinalColor = OrdinalColor.instantiate().apply {
            colors(arrayOf("#7F21F7", "#AD71FA", "#B47EFA", "#D2B0FF"))
        }
        with(tagCloud) {
            colorScale(ordinalColor)
            angles(arrayOf(-90.0, 0.0, 90.0))
            colorRange().enabled(true)
            colorRange().colorLineSize(15.0)
            data(data);
        }
        binding.fragmentHotTopicWordCloudWc.setChart(tagCloud);
    }

    private fun setChartClickListener() {
        tagCloud.setOnClickListener(object :
            ListenersInterface.OnClickListener(arrayOf("x", "category")) {
            override fun onClick(event: Event) {
                val clickedTag = event.data["x"]
                val category = event.data["category"]
                requireActivity().runOnUiThread {
                    Toast.makeText(requireContext(), "$clickedTag", Toast.LENGTH_SHORT).show()
                    Log.d("로그", "$clickedTag, Category: $category")
                    commitFragment(clickedTag.toString())
                }
            }
        })
    }

    private fun commitFragment(clickedTag : String) {
        childFragmentManager.beginTransaction().apply {
            add(R.id.fragment_hot_topic_word_cloud_fl, HotTopicDetailFragment(clickedTag))
            commit()
        }
    }

    override fun onResume() {
        super.onResume()
        setChartClickListener()
    }

    companion object {
        const val HOTTOPICWORDSCOUNT = "10"
    }

}
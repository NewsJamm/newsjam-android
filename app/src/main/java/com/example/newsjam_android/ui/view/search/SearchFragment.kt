package com.example.newsjam_android.ui.view.search

import android.graphics.Rect
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.example.newsjam_android.R
import com.example.newsjam_android.data.model.RankType
import com.example.newsjam_android.databinding.FragmentSearchBinding
import com.example.newsjam_android.ui.base.BaseFragment
import com.example.newsjam_android.ui.view.adapter.SearchHotTopicAdapter

class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {
    private var isKeyboardVisible = false // 키보드 상태를 저장하기 위한 변수
    private lateinit var globalLayoutListener: ViewTreeObserver.OnGlobalLayoutListener
    private lateinit var searchMultiAdapter: SearchHotTopicAdapter
    private lateinit var rankList : MutableList<RankType>
    var resultList : MutableList<RankType> = mutableListOf(
        RankType.SearchResultType(
            publish = "스브스뉴스",
            title ="코인에 빠진 대통령, 엘살바도르의 운명은?",
            arriveTime = "3분전"
        ),
        RankType.SearchResultType(
            publish = "스브스뉴스",
            title ="코인에 빠진 김진하",
            arriveTime = "3분전"
        ),
        RankType.SearchResultType(
            publish = "스브스뉴스",
            title ="코인에 빠진 김준하",
            arriveTime = "2분전"
        ),
        RankType.SearchResultType(
            publish = "스브스뉴스",
            title ="코인에 빠진 곰돌이",
            arriveTime = "3분전"
        ),
        RankType.SearchResultType(
            publish = "스브스뉴스",
            title ="코인에 빠진 대통령, 엘살바도르의 운명은?",
            arriveTime = "1분전"
        )
    )
    override fun setLayout() {
        observeKeyboardState()
        initRecyclerView()
        changeSearchResultAdapter()
    }

    private fun initRecyclerView() {
        searchMultiAdapter = SearchHotTopicAdapter()
        changeRankRecyclerView()
        binding.fragmentSearchRv.adapter = searchMultiAdapter
    }

    private fun changeRankRecyclerView(){
        showHotTopicTitle()
        rankList = mutableListOf()
        for (i in 1 .. 10){
            rankList.add(RankType.HopTopicType(
                "$i",
                "순위 $i"
            ))
        }
        searchMultiAdapter.submitList(rankList.toList())
    }
    private fun changeSearchResultAdapter(){
        binding.fragmentSearchEt.setupSearchButtonWithAction()
    }

    private fun showHotTopicTitle(){
        binding.fragmentSearchTitleTv.visibility = View.VISIBLE
    }

    private fun hideHotTopicTitle(){
        binding.fragmentSearchTitleTv.visibility = View.GONE
    }

    private fun EditText.setupSearchButtonWithAction() {
        setOnTouchListener(View.OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= (this.right - this.compoundPaddingRight)) {
                    hideHotTopicTitle()
                    val query = binding.fragmentSearchEt.text.toString()
                    val searchList = resultList.filter{
                        if (it is RankType.SearchResultType) {
                            it.arriveTime.contains(query) || it.publish.contains(query) || it.title.contains(query)
                        } else {
                            false
                        }
                    }
                    if(searchList.isEmpty()){
                        changeRankRecyclerView()
                    }
                    else {
                        searchMultiAdapter.submitList(searchList)
                    }
                    return@OnTouchListener true
                }
                v.performClick()
            }
            return@OnTouchListener false
        })
    }
    private fun observeKeyboardState() {
        globalLayoutListener = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = Rect()
            requireActivity().window.decorView.getWindowVisibleDisplayFrame(rect)
            val screenHeight = requireActivity().window.decorView.height
            val keypadHeight = screenHeight - rect.bottom

            val isKeyboardNowVisible = keypadHeight > screenHeight * 0.15

            if (isKeyboardNowVisible != isKeyboardVisible) {
                isKeyboardVisible = isKeyboardNowVisible

                if (isKeyboardVisible) {
                    updateEditTextColor(true)
                    Log.d("로그", "키보드 올라옴")
                } else {
                    updateEditTextColor(false)
                    Log.d("로그", "키보드 내려감")
                }
            }
        }
        // 리스너를 추가
        requireActivity().window.decorView.viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)
    }

    private fun updateEditTextColor(isActive: Boolean) {
        val colorRes = if (isActive) R.color.main_red else R.color.edit_text_color

        binding.fragmentSearchEt.setBackgroundTintList(
            ContextCompat.getColorStateList(requireContext(), colorRes)
        )

        val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_search)
        drawable?.let {
            DrawableCompat.setTint(it, ContextCompat.getColor(requireContext(), colorRes))
            binding.fragmentSearchEt.setCompoundDrawablesWithIntrinsicBounds(null, null, it, null)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // 리스너를 제거
        requireActivity().window.decorView.viewTreeObserver.removeOnGlobalLayoutListener(globalLayoutListener)
    }
}

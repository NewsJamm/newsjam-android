package com.example.newsjam_android.ui.view.account

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.data.di.AppCoroutineScope
import com.example.data.extensions.TokenManager
import com.example.data.model.TagData
import com.example.data.request.InterestingKeyWordsDTO
import com.example.newsjam_android.R
import com.example.newsjam_android.databinding.FragmentLikeChoiceBinding
import com.example.newsjam_android.ui.base.BaseFragment
import com.example.newsjam_android.ui.view.adapter.LikeChoiceAdapter
import com.example.newsjam_android.ui.view.listener.ItemAddListener
import com.example.newsjam_android.ui.view.viewmodel.MainViewModel
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class LikeChoiceFragment : BaseFragment<FragmentLikeChoiceBinding>(R.layout.fragment_like_choice),
    ItemAddListener {
    @Inject
    lateinit var tokenManager: TokenManager


    val mainViewModel : MainViewModel by activityViewModels()

    private lateinit var likeChoiceAdapter: LikeChoiceAdapter
    private var selectTagList: MutableList<String> = mutableListOf()
    override fun setLayout() {
        initAdapter()
        setRecyclerView()
        initSelectTagList()
        setOnClickView()
    }

    private fun initAdapter() {
        likeChoiceAdapter = LikeChoiceAdapter(this)
    }


    private fun initSelectTagList() {
        runBlocking {
            selectTagList = tokenManager.getList().first().distinct().toMutableList()
            Log.d("리스트get", "$selectTagList")
            loadTags()
        }
    }

    private fun saveTagList() {
        runBlocking{
            val selectList = selectTagList.toList()
            tokenManager.saveList(selectList)
            Log.d("리스트ㄴㄴㄴ","ㅇㄴㄹ")
        }
        mainViewModel.postInterestingKeyWords(InterestingKeyWordsDTO(selectTagList))
    }

    private fun setOnClickView() {
        val page = arguments?.getString("page")
        if (!page.isNullOrEmpty()) {
            binding.appCompatButton.text = "저장"
        }
        binding.appCompatButton.tag = page
        binding.appCompatButton.setOnClickListener {
            if (it.tag == "my_page") {
                findNavController().popBackStack()
            } else {
                findNavController().navigate(R.id.action_likeChoiceFragment_to_hotNewsConfirmFragment)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        saveTagList()
    }

    override fun onResume() {
        super.onResume()
        initSelectTagList()
    }

    private fun loadTags() {
        val tagList = listOf(
            "정치", "경제", "사회", "생활/문화", "IT/과학", "금융", "재테크", "부동산",
            "연예", "영화", "예술", "TV", "엔터테인먼트", "여행", "야구", "해외야구",
            "축구", "해외축구", "농구", "배구", "골프", "e스포츠"
        ).map { name ->
            TagData(
                isSelected = selectTagList.contains(name),
                title = name
            )
        }
        Log.d("태그리스트", "$tagList")
        likeChoiceAdapter.submitList(tagList)
    }

    private fun setRecyclerView() {
        binding.fragmentLikeFlexBoxRv.apply {
            layoutManager = FlexboxLayoutManager(requireActivity()).apply {
                flexWrap = FlexWrap.WRAP
                flexDirection = FlexDirection.ROW
            }
            adapter = likeChoiceAdapter
        }
    }


    override fun add(category: String) {
        selectTagList.add(category)
        Log.d("리스트add", "$selectTagList")
    }

    override fun remove(category: String) {
        selectTagList.removeAll { it == category }
        Log.d("리스트remove", "$selectTagList")
    }

}
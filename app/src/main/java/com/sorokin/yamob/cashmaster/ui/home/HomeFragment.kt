package com.sorokin.yamob.cashmaster.ui.home

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.delegateadapter.delegate.diff.IComparableItem

import com.sorokin.yamob.cashmaster.R
import com.sorokin.yamob.cashmaster.ui.adapter.MyDiffUtilCompositeAdapter
import com.sorokin.yamob.cashmaster.ui.base.BaseActivityFragment
import com.sorokin.yamob.cashmaster.ui.home.delegate_adapter.CategoryDelegateAdapter
import com.sorokin.yamob.cashmaster.ui.home.delegate_adapter.CategoryItem
import com.sorokin.yamob.cashmaster.ui.home.delegate_adapter.TextDelegateAdapter
import com.sorokin.yamob.cashmaster.ui.home.delegate_adapter.TextItem
import com.sorokin.yamob.cashmaster.util.observe
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseActivityFragment<HomeViewModel>() {
    companion object {
        fun newInstance() = HomeFragment()
    }

    lateinit var mainRVAdapter: MyDiffUtilCompositeAdapter
    lateinit var layoutManager: GridLayoutManager

    var data = mutableListOf<IComparableItem>()

    override fun provideViewModel(): HomeViewModel
            = ViewModelProviders.of(this, viewModelFactory)[HomeViewModel::class.java]

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.categories.observe(this){
            data.clear()
            data.add(TextItem("Income categories"))
            it.take(3).forEachIndexed { idx, str ->
                data.add(CategoryItem(idx, str))
            }
            data.add(TextItem("Income categories"))
            it.drop(3).forEachIndexed { idx, str ->
                data.add(CategoryItem(idx, str))
            }
            mainRVAdapter.swapData(data)
            mainRVAdapter.notifyDataSetChanged()
        }


        layoutManager = GridLayoutManager(requireContext(), 4, GridLayoutManager.VERTICAL, false)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {
                return when(mainRVAdapter.getItem(position)){
                    is TextItem -> layoutManager.spanCount
                    else -> 1
                }
            }

        }
        rv_main.layoutManager = layoutManager

        mainRVAdapter = MyDiffUtilCompositeAdapter.Builder()
                .add(TextDelegateAdapter())
                .add(CategoryDelegateAdapter())
                .build()

        rv_main.adapter = mainRVAdapter

    }

}

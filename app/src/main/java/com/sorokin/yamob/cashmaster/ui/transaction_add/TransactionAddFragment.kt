package com.sorokin.yamob.cashmaster.ui.transaction_add


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sorokin.yamob.cashmaster.R
import com.sorokin.yamob.cashmaster.ui.base.BaseActivityFragment
import kotlinx.android.synthetic.main.fragment_add_transaction.*
import android.text.Editable
import android.text.TextWatcher
import com.sorokin.yamob.cashmaster.util.observe
import timber.log.Timber
import android.widget.ArrayAdapter




class TransactionAddFragment : BaseActivityFragment<TransactionAddViewModel>() {

    lateinit var categoryAdapter : ArrayAdapter<String>
    lateinit var currencyAdapter : ArrayAdapter<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rg_trans_type.onSelectedItemChanged = {
            Timber.i("Type clicked: ${viewModel.transactionTypes.value?.get(it)}")
        }
        viewModel.transactionTypes.observe(this){
            rg_trans_type.setItems(it)
        }

        rg_trans_account.onSelectedItemChanged = {
            Timber.i("Account clicked: ${viewModel.accounts.value?.get(it)}")
        }
        viewModel.accounts.observe(this){
            rg_trans_account.setItems(it)
        }

        categoryAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line)
        //et_category.dropDownVerticalOffset = 0
        et_category.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus){
                Timber.i("TOP = ${et_category.top}, scrollY = ${et_category.scrollY}")
                scroll_home.smoothScrollTo(0, et_category.scrollY)
            }
        }
        et_category.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //et_category.dropDownVerticalOffset = 0
            }

        })
        et_category.setAdapter(categoryAdapter)
        viewModel.categories.observe(this){
            categoryAdapter.clear()
            categoryAdapter.addAll(it)
            categoryAdapter.notifyDataSetChanged()
        }

        currencyAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item)
        spinner_currencies.adapter = currencyAdapter
        viewModel.currencies.observe(this){
            currencyAdapter.clear()
            currencyAdapter.addAll(it)
        }

    }

    override fun provideViewModel(): TransactionAddViewModel = getViewModel(viewModelFactory)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_transaction, container, false)
    }



}

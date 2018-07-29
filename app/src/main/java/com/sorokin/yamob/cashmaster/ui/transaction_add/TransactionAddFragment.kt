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
import com.sorokin.yamob.cashmaster.data.entity.MoneyTransaction


class TransactionAddFragment : BaseActivityFragment<TransactionAddViewModel>() {

    lateinit var categoryAdapter : ArrayAdapter<String>
    lateinit var currencyAdapter : ArrayAdapter<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = TransactionAddFragmentArgs.fromBundle(arguments)
        viewModel.setTransactionType(args.transactionType)
        viewModel.transactionType.observe(this){
            if(it == MoneyTransaction.INCOME){
                tv_transaction_type.text = "Income"
            }else if(it == MoneyTransaction.EXPENSE){
                tv_transaction_type.text = "Expense"
            }
        }
        if(args.transactionType == MoneyTransaction.INCOME){
            viewModel.setTargetFromId(args.target)
            viewModel.targetFrom.observe(this){
                tv_from.text = it.name
            }
            viewModel.setWalletToId(args.walletTo)
            viewModel.walletTo.observe(this){
                tv_to.text = it.name
            }
        }else if(args.transactionType == MoneyTransaction.EXPENSE){
            viewModel.setWalletFromId(args.walletFrom)
            viewModel.walletFrom.observe(this){
                tv_from.text = it.name
            }
            viewModel.setTargetToId(args.target)
            viewModel.targetTo.observe(this){
                tv_to.text = it.name
            }
        }

    }

    override fun provideViewModel(): TransactionAddViewModel = getViewModel(viewModelFactory)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_transaction, container, false)

    }



}

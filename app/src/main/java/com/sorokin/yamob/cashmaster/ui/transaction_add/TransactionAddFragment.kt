package com.sorokin.yamob.cashmaster.ui.transaction_add


import android.content.Context
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
import android.widget.Toast
import androidx.navigation.Navigation
import com.sorokin.yamob.cashmaster.data.entity.MoneyTransaction
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager


class TransactionAddFragment : BaseActivityFragment<TransactionAddViewModel>() {

    lateinit var currencyAdapter : ArrayAdapter<String>


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = TransactionAddFragmentArgs.fromBundle(arguments)
        viewModel.setTransactionType(args.transactionType)
        viewModel.transactionType.observe(this){
            if(it == MoneyTransaction.INCOME){
                tv_transaction_type.setText(R.string.income)
            }else if(it == MoneyTransaction.EXPENSE){
                tv_transaction_type.setText(R.string.expense)
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

        currencyAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item)
        currencyAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        spinner_currencies.adapter = currencyAdapter

        viewModel.currencies.observe(this){
            currencyAdapter.addAll(it)
            currencyAdapter.notifyDataSetChanged()
            spinner_currencies.setSelection(it.indexOfFirst { cur -> cur == (viewModel.walletTo.value?.currency ?: viewModel.walletFrom.value?.currency ?: "RUB") })
        }

        btn_confirm.setOnClickListener{
            val amount = et_sum.text.toString().toDoubleOrNull() ?: -5.0
            if(amount <= 0){
                til_sum.error = getString(R.string.error_invalid_number)
            }else{
                val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
                Toast.makeText(requireActivity(), R.string.transaction_added, Toast.LENGTH_SHORT).show()
                Navigation.findNavController(it).navigate(R.id.nav_action_add_trans_completed)
            }
        }
    }

    override fun provideViewModel(): TransactionAddViewModel = getViewModel(viewModelFactory)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_transaction, container, false)

    }



}

package com.sorokin.yamob.cashmaster.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.sorokin.yamob.cashmaster.R
import com.sorokin.yamob.cashmaster.ui.base.BaseActivity
import com.sorokin.yamob.cashmaster.ui.shared.SharedViewModel
import com.sorokin.yamob.cashmaster.util.observe
import com.sorokin.yamob.mycash.util.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel>() {

    override fun onSupportNavigateUp(): Boolean =
            findNavController(R.id.fragment_main_nav_host).navigateUp()

    override fun provideViewModel(): MainViewModel = getViewModel(viewModelFactory)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var sharedViewModel: SharedViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()

        initViewModel()

        initNav()

        if(savedInstanceState == null){
            navigation.selectedItemId = R.id.nav_tab_transactions
        }
    }

    private fun initView(){
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        floatingActionButton.setOnClickListener {
            Toast.makeText(this, R.string.this_button_will_work, Toast.LENGTH_LONG).show()
        }
    }

    fun initViewModel() {
        sharedViewModel = ViewModelProviders.of(this, viewModelFactory)[SharedViewModel::class.java]

        sharedViewModel.toolbarIsVisible.observe(this) {
            toolbar.visibility = if (it) View.VISIBLE else View.GONE
        }

        sharedViewModel.fabIsVisible.observe(this) {
            if(it) floatingActionButton.show()
            else floatingActionButton.hide()
        }

        sharedViewModel.bottomNavigationIsVisible.observe(this){
            navigation.visibility = if(it) View.VISIBLE else View.GONE
        }
    }

    fun initNav(){
        // FIX : Navigation components doesn't set title on launch or after orientation changes.
        title = findNavController(R.id.fragment_main_nav_host).currentDestination.label
        NavigationUI.setupWithNavController(toolbar, findNavController(R.id.fragment_main_nav_host))
        NavigationUI.setupWithNavController(navigation, findNavController(R.id.fragment_main_nav_host))
    }

}

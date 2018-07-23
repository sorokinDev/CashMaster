package com.sorokin.yamob.cashmaster.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.sorokin.yamob.cashmaster.R
import com.sorokin.yamob.cashmaster.ui.about.AboutFragment
import com.sorokin.yamob.cashmaster.ui.home.HomeFragment
import com.sorokin.yamob.cashmaster.ui.settings.SettingsFragment
import com.sorokin.yamob.cashmaster.ui.shared.SharedViewModel
import com.sorokin.yamob.cashmaster.util.Screens
import com.sorokin.yamob.cashmaster.util.observe
import com.sorokin.yamob.mycash.util.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel>() {
    override fun provideViewModel(): MainViewModel = getViewModel(viewModelFactory)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var sharedViewModel: SharedViewModel

    @Inject lateinit var navHolder: NavigatorHolder

    @Inject lateinit var homeFragment: HomeFragment
    @Inject lateinit var settingsFragment: SettingsFragment
    @Inject lateinit var aboutFragment: AboutFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()

        initViewModel()

        initNav()

        if(savedInstanceState == null){
            navigation.selectedItemId = R.id.nav_home
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

        sharedViewModel.toolbarIsVisible.observe(this, {
            toolbar.visibility = if (it) View.VISIBLE else View.GONE
        })

        sharedViewModel.title.observe(this, {
            title = it
        }, {
            setTitle(resources.getString(R.string.app_name))
        })

        sharedViewModel.fabIsVisible.observe(this, {
            if(it) floatingActionButton.show()
            else floatingActionButton.hide()
        })
    }

    fun initNav(){
        navigation.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.nav_home -> viewModel.router.newRootScreen(Screens.HOME)
                R.id.nav_settings -> viewModel.router.newRootScreen(Screens.SETTINGS)
                else -> viewModel.router.newRootScreen(Screens.HOME)
            }
            true
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navHolder.removeNavigator()
        super.onPause()
    }

    val navigator = object : SupportAppNavigator(this, R.id.content){
        override fun setupFragmentTransactionAnimation(command: Command?, currentFragment: Fragment?, nextFragment: Fragment?, fragmentTransaction: FragmentTransaction?) {
            super.setupFragmentTransactionAnimation(command, currentFragment, nextFragment, fragmentTransaction)
        }

        override fun createActivityIntent(context: Context?, screenKey: String?, data: Any?): Intent? {
            return null
        }

        override fun forward(command: Forward?) {
            super.forward(command)
        }


        override fun createFragment(screenKey: String?, data: Any?): Fragment?  = when(screenKey){
            Screens.HOME -> homeFragment
            Screens.SETTINGS -> settingsFragment
            Screens.ABOUT -> aboutFragment
            else -> homeFragment
        }
    }
}

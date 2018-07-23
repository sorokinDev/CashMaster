package com.sorokin.yamob.cashmaster.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.util.Log
import android.view.MenuItem
import android.view.View
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
import kotlinx.android.synthetic.main.app_bar_main.*
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.SupportAppNavigator
import ru.terrakok.cicerone.commands.Forward
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel>(), NavigationView.OnNavigationItemSelectedListener {
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


        if(savedInstanceState == null){
            nav_view.setCheckedItem(R.id.nav_home);
            viewModel.router.newRootScreen(Screens.HOME)
        }
    }

    private fun initView(){
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    fun initViewModel() {
        sharedViewModel = ViewModelProviders.of(this, viewModelFactory)[SharedViewModel::class.java]

        sharedViewModel.toolbarIsVisible.observe(this, {
            toolbar.visibility = if (it) View.VISIBLE else View.GONE
        })

        sharedViewModel.title.observe(this, {
            title = it
            if(supportFragmentManager.backStackEntryCount > 0){

                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

            }else{

                drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                val toggle = ActionBarDrawerToggle(
                        this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
                drawer_layout.addDrawerListener(toggle)
                toggle.syncState()

            }
            Log.e("BACKSTACK_TITLE", supportFragmentManager.backStackEntryCount.toString())
        }, {
            setTitle(resources.getString(R.string.app_name))
        })
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        Log.d("OPTION", "UNKNOWN")
        when (id) {
            android.R.id.home -> { Log.d("OPTION", "HOME"); viewModel.router.exit() }
        }
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                viewModel.router.newRootScreen(Screens.HOME)
            }
            R.id.nav_settings -> {
                viewModel.router.navigateTo(Screens.SETTINGS)
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
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

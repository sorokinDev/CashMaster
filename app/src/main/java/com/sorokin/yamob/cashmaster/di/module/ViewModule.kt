package com.sorokin.yamob.cashmaster.di.module

import com.sorokin.yamob.cashmaster.ui.home.HomeFragment
import com.sorokin.yamob.cashmaster.ui.home.HomeViewModel
import com.sorokin.yamob.cashmaster.ui.main.MainActivity
import com.sorokin.yamob.cashmaster.ui.settings.SettingsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ViewModule {

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun homeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun settingsFragment(): SettingsFragment
}
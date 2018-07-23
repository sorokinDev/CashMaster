package com.sorokin.yamob.cashmaster.di.component

import android.app.Application
import com.sorokin.yamob.cashmaster.App
import com.sorokin.yamob.cashmaster.di.module.AppModule
import com.sorokin.yamob.cashmaster.di.module.RepositoryModule
import com.sorokin.yamob.cashmaster.di.module.ViewModelModule
import com.sorokin.yamob.cashmaster.di.module.ViewModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AndroidSupportInjectionModule::class,
            AppModule::class,
            ViewModule::class,
            ViewModelModule::class,
            RepositoryModule::class
        ]
)
interface AppComponent: AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(app: Application):Builder

        fun build(): AppComponent
    }
}
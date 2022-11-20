package io.lostimagin4tion.sovcombankchallenge.dagger

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import io.lostimagin4tion.sovcombankchallenge.ui.screens.authorization.AuthorizationViewModel
import io.lostimagin4tion.sovcombankchallenge.ui.screens.main.MainViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, RepositoriesModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(mainViewModel: MainViewModel)
    fun inject(authorizationViewModel: AuthorizationViewModel)
}
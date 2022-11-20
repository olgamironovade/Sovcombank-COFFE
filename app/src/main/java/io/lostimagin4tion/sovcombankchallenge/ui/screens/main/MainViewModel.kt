package io.lostimagin4tion.sovcombankchallenge.ui.screens.main

import androidx.lifecycle.ViewModel
import io.lostimagin4tion.sovcombankchallenge.SovcombankChallengeApplication
import io.lostimagin4tion.sovcombankchallenge.dagger.AppComponent
import io.lostimagin4tion.sovcombankchallenge.state.Session
import io.lostimagin4tion.sovcombankchallenge.state.Settings
import javax.inject.Inject

class MainViewModel(appComponent: AppComponent = SovcombankChallengeApplication.appComponent) : ViewModel() {
    @Inject
    lateinit var session: Session
    @Inject
    lateinit var settings: Settings

    val isLogged by lazy { session.isLogged }

    val theme by lazy { settings.themeSetting }

    init {
        appComponent.inject(this)
    }
}

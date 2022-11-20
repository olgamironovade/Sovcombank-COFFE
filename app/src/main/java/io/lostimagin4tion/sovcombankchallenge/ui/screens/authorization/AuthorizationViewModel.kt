package io.lostimagin4tion.sovcombankchallenge.ui.screens.authorization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.lostimagin4tion.sovcombankchallenge.R
import io.lostimagin4tion.sovcombankchallenge.SovcombankChallengeApplication
import io.lostimagin4tion.sovcombankchallenge.dagger.AppComponent
import io.lostimagin4tion.sovcombankchallenge.domain.repositories.IAuthorizationRepository
import io.lostimagin4tion.sovcombankchallenge.ui.utils.MutableResultFlow
import io.lostimagin4tion.sovcombankchallenge.ui.utils.loadOrError
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthorizationViewModel(appComponent: AppComponent = SovcombankChallengeApplication.appComponent) : ViewModel() {
    @Inject
    lateinit var authRepository: IAuthorizationRepository

    val loginResult = MutableResultFlow<Unit>()

    init {
        appComponent.inject(this)
    }

    fun login(username: String, password: String) = viewModelScope.launch {
        loginResult.loadOrError(R.string.login_error_message) {
            authRepository.auth(username, password)
        }
    }
}
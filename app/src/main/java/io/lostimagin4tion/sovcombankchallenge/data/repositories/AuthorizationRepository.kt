package io.lostimagin4tion.sovcombankchallenge.data.repositories

import io.lostimagin4tion.sovcombankchallenge.data.api.AuthRequest
import io.lostimagin4tion.sovcombankchallenge.data.api.SovcombankApi
import io.lostimagin4tion.sovcombankchallenge.domain.repositories.IAuthorizationRepository
import io.lostimagin4tion.sovcombankchallenge.state.Session
import javax.inject.Inject

class AuthorizationRepository @Inject constructor(
    private val sovcombankApi: SovcombankApi,
    private val session: Session
) : IAuthorizationRepository {
    override suspend fun auth(username: String, password: String) = withIO {
        sovcombankApi.auth(
            AuthRequest(
                username = username,
                password = password,
            )
        ).let {
            session.changeAuthCredentials(
                token = it.auth_token,
                refreshToken = it.refresh ?: "missing"
            )
            session.changeCurrentUserId(it.id)
        }
    }
}
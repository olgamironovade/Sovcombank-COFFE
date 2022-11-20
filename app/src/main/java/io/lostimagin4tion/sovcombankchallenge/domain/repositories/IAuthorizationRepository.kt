package io.lostimagin4tion.sovcombankchallenge.domain.repositories

interface IAuthorizationRepository {
    suspend fun auth(username: String, password: String)
}
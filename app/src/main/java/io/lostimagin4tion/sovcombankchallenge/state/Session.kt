package io.lostimagin4tion.sovcombankchallenge.state

import android.content.Context
import androidx.core.content.edit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class Session(context: Context) {

    private val sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val _refreshToken = MutableStateFlow(sharedPreferences.getString(REFRESH_TOKEN_KEY, "").orEmpty())
    private val _token = MutableStateFlow(sharedPreferences.getString(TOKEN_KEY, "").orEmpty())

    val refreshToken: StateFlow<String> = _refreshToken
    val token: StateFlow<String> = _token

    fun changeAuthCredentials(token: String, refreshToken: String) {
        sharedPreferences.edit {
            putString(TOKEN_KEY, token)
            putString(REFRESH_TOKEN_KEY, refreshToken)
        }
        _token.value = token
        _refreshToken.value = refreshToken
    }

    private val _currentUserId = MutableStateFlow(sharedPreferences.getLong(USER_ID_KEY, -1))
    val currentUserId: StateFlow<Long> = _currentUserId
    fun changeCurrentUserId(value: Long) {
        sharedPreferences.edit { putLong(USER_ID_KEY, value) }
        _currentUserId.value = value
    }

    private fun checkLogged(token: String, refresh: String) = listOf(token, refresh).all { it.isNotEmpty() }
    val isLogged = combine(token, refreshToken, ::checkLogged)
        .stateIn(scope, SharingStarted.Eagerly, initialValue = checkLogged(token.value, refreshToken.value))

    fun reset() {
        changeAuthCredentials("", "")
        changeCurrentUserId(-1)
    }

    companion object {
        private const val PREFERENCES_NAME = "session"
        private const val TOKEN_KEY = "token"
        private const val REFRESH_TOKEN_KEY = "refresh_token"
        private const val USER_ID_KEY = "user_id"
    }

    // Events (no data, just dispatch update to subscribers)

    val taskEdit = EventFlow() // some task was edited
    val sprintEdit = EventFlow() // sprint was edited
}

/**
 * An empty class which describes basic event without any data (for the sake of update only)
 */
class Event
@Suppress("FunctionName")
fun EventFlow() = MutableSharedFlow<Event>()

suspend fun MutableSharedFlow<Event>.postUpdate() = emit(Event())
fun MutableSharedFlow<Event>.tryPostUpdate() = tryEmit(Event())

fun CoroutineScope.subscribeToAll(vararg flows: Flow<*>, action: () -> Unit) {
    flows.forEach {
        launch {
            it.collect { action() }
        }
    }
}

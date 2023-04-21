package di

import utils.answer.Answer
import utils.answer.BaseRemoteDataSource
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import model.LoginRequest
import utils.answer.onSuccess
import org.koin.dsl.module

val authModule = module {
    //single { SettingsAuthDataSource(get()) }
    single { AuthRepository(get()) }
    single { RemoteAuthDataSource(get()) }
}

class AuthRepository(
    private val remote: RemoteAuthDataSource,
    //private val settings: SettingsAuthDataSource
) {

    suspend fun authenticate(): Answer<Unit> {
        return remote.authenticate()
    }

    suspend fun logIn(phone: String): Answer<Unit> {
        val answer = remote.logIn(phone)
        answer.onSuccess {
            //settings.saveToken(it.token)
        }
        return answer
    }
}

class RemoteAuthDataSource(
    private val httpClient: HttpClient,
    //private val settings: SettingsAuthDataSource
) : BaseRemoteDataSource() {

    suspend fun authenticate(): Answer<Unit> {
        return apiCall {
            httpClient.get {
                //header(HttpHeaders.Authorization, settings.fetchToken())
                url("authenticate")
            }
        }
    }

    suspend fun logIn(phone: String): Answer<Unit> {
        return apiCall {
            httpClient.post {
                setBody(LoginRequest(phone))
                url("login")
            }
        }
    }
}

class SettingsAuthDataSource(
    private val settings: Settings
) {

    fun saveToken(token: String) {
        settings.putString(tokenKey, "Bearer $token")
    }

    fun fetchToken(): String {
        return settings[tokenKey, ""]
    }

    companion object {
        private val tokenKey = "tokenKey"
    }
}
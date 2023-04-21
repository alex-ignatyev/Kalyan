package domain.repository

import data.RemoteAuthDataSource
import data.SettingsAuthDataSource
import model.TokenResponse
import utils.answer.Answer
import utils.answer.onSuccess

class AuthRepositoryImpl(
    private val remote: RemoteAuthDataSource,
    private val settings: SettingsAuthDataSource
) : AuthRepository {

    override suspend fun logIn(phone: String): Answer<Unit> {
        return remote.logIn(phone)
    }

    override suspend fun sendSmsCode(code: String): Answer<TokenResponse> {
        val answer = remote.sendSmsCode(code)
        answer.onSuccess {
            settings.saveToken(it.token)
        }
        return answer
    }
}

interface AuthRepository {
    suspend fun logIn(phone: String): Answer<Unit>
    suspend fun sendSmsCode(code: String): Answer<TokenResponse>
}

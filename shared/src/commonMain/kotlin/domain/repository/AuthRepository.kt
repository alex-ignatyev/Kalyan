package domain.repository

import data.RemoteAuthDataSource
import data.SettingsDataSource
import model.data.request.AccountCreateRequest
import model.data.request.AccountForgotRequest
import model.data.request.AccountLoginRequest
import model.data.response.TokenResponse
import utils.answer.Answer
import utils.answer.onSuccess

class AuthRepositoryImpl(
    private val remote: RemoteAuthDataSource,
    private val settings: SettingsDataSource
) : AuthRepository {

    override suspend fun create(request: AccountCreateRequest): Answer<Unit> {
        return remote.create(request)
    }

    override suspend fun login(request: AccountLoginRequest): Answer<TokenResponse> {
        val answer =  remote.login(request)
        answer.onSuccess {
            settings.saveToken(it.token)
        }
        return answer
    }

    override suspend fun forgot(request: AccountForgotRequest): Answer<Unit> {
        return remote.forgot(request)
    }
}

interface AuthRepository {
    suspend fun create(request: AccountCreateRequest): Answer<Unit>
    suspend fun login(request: AccountLoginRequest): Answer<TokenResponse>
    suspend fun forgot(request: AccountForgotRequest): Answer<Unit>
}

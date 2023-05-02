package domain.repository

import data.RemoteAuthDataSource
import data.SettingsDataSource
import model.auth.request.AccountCreateRequest
import model.auth.request.AccountForgotRequest
import model.auth.request.AccountLoginRequest
import model.auth.TokenResponse
import utils.answer.Answer
import utils.answer.onSuccess

class AuthRepositoryImpl(
    private val remote: RemoteAuthDataSource,
    private val settings: SettingsDataSource
) : AuthRepository {

    override suspend fun create(request: AccountCreateRequest): Answer<Unit> {
        return remote.createAccount(request)
    }

    override suspend fun login(request: AccountLoginRequest): Answer<TokenResponse> {
        val answer =  remote.login(request)
        answer.onSuccess {
            settings.saveToken(it.token)
        }
        return answer
    }

    override suspend fun forgot(request: AccountForgotRequest): Answer<Unit> {
        return remote.forgotPassword(request)
    }
}

interface AuthRepository {
    suspend fun create(request: AccountCreateRequest): Answer<Unit>
    suspend fun login(request: AccountLoginRequest): Answer<TokenResponse>
    suspend fun forgot(request: AccountForgotRequest): Answer<Unit>
}

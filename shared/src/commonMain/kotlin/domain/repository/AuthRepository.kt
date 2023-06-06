package domain.repository

import com.kalyan.shared.AppRes
import data.RemoteAuthDataSource
import data.SettingsDataSource
import model.data.auth.request.AccountCreateRequest
import model.data.auth.request.AccountForgotRequest
import model.data.auth.request.AccountLoginRequest
import utils.answer.Answer
import utils.answer.ErrorCode.InternalError
import utils.answer.onFailure
import utils.answer.onSuccess

class AuthRepositoryImpl(
    private val remote: RemoteAuthDataSource,
    private val settings: SettingsDataSource
) : AuthRepository {

    override suspend fun authorize(): Answer<Unit> {
        return remote.authorize(settings.getToken())
    }

    override suspend fun create(request: AccountCreateRequest): Answer<Unit> {
        return remote.createAccount(request)
    }

    override suspend fun login(request: AccountLoginRequest): Answer<Unit> {
        remote.login(request).onSuccess {
            if (it.userId == null || it.token == null || it.isAdmin == null) {
                return Answer.failure(code = InternalError, message = AppRes.string.error_something_went_wrong)
            }
            settings.saveInfo(it.token, it.userId, it.isAdmin)
        }.onFailure {
            return Answer.failure(it.code, it.message)
        }
        return Answer.success(Unit)
    }

    override suspend fun forgot(request: AccountForgotRequest): Answer<Unit> {
        return remote.forgotPassword(request)
    }
}

interface AuthRepository {
    suspend fun authorize(): Answer<Unit>
    suspend fun create(request: AccountCreateRequest): Answer<Unit>
    suspend fun login(request: AccountLoginRequest): Answer<Unit>
    suspend fun forgot(request: AccountForgotRequest): Answer<Unit>
}

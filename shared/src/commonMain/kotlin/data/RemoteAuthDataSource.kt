package data

import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import model.auth.request.AccountCreateRequest
import model.auth.request.AccountForgotRequest
import model.auth.request.AccountLoginRequest
import model.auth.TokenResponse
import utils.answer.Answer
import utils.answer.BaseRemoteDataSource

class RemoteAuthDataSource(
    private val httpClient: HttpClient
) : BaseRemoteDataSource() {

    suspend fun createAccount(request: AccountCreateRequest): Answer<Unit> {
        return apiCall {
            httpClient.post {
                setBody(request)
                url("account_create")
            }
        }
    }

    suspend fun login(request: AccountLoginRequest): Answer<TokenResponse> {
        return apiCall {
            httpClient.post {
                setBody(request)
                url("account_login")
            }
        }
    }

    suspend fun forgotPassword(request: AccountForgotRequest): Answer<Unit> {
        return apiCall {
            httpClient.post {
                setBody(request)
                url("account_forgot")
            }
        }
    }
}

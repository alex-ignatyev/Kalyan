package data

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.HttpHeaders
import model.data.auth.TokenResponse
import model.data.auth.request.AccountCreateRequest
import model.data.auth.request.AccountForgotRequest
import model.data.auth.request.AccountLoginRequest
import utils.answer.Answer
import utils.answer.BaseRemoteDataSource

class RemoteAuthDataSource(
    private val httpClient: HttpClient
) : BaseRemoteDataSource() {

    suspend fun authorize(token: String): Answer<Unit> {
        return apiCall {
            httpClient.get {
                header(HttpHeaders.Authorization, token)
                url("authorize")
            }
        }
    }

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

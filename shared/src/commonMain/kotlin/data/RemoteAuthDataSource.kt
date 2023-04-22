package data

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.HttpHeaders
import model.LoginRequest
import model.SmsRequest
import model.TokenResponse
import utils.answer.Answer
import utils.answer.BaseRemoteDataSource

class RemoteAuthDataSource(
    private val httpClient: HttpClient
) : BaseRemoteDataSource() {

    suspend fun test(token: String): Answer<Unit> {
        return apiCall {
            httpClient.get {
                header(HttpHeaders.Authorization, token)
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

    suspend fun sendSmsCode(code: String): Answer<TokenResponse> {
        return apiCall {
            httpClient.post {
                setBody(SmsRequest("", code))
                url("sms")
            }
        }
    }
}

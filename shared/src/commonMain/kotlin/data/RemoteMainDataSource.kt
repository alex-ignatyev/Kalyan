package data

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.url
import io.ktor.http.HttpHeaders
import model.Tobaccos
import utils.answer.Answer
import utils.answer.BaseRemoteDataSource

class RemoteMainDataSource(
    private val httpClient: HttpClient
) : BaseRemoteDataSource() {

    suspend fun getTobaccos(token: String): Answer<Tobaccos> {
        return apiCall {
            httpClient.get {
                header(HttpHeaders.Authorization, token)
                url("tobaccos")
            }
        }
    }
}

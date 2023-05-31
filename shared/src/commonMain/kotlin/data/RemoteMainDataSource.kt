package data

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.HttpHeaders
import model.tobacco.TobaccoInfoRequest
import model.tobacco.TobaccoInfoResponse
import model.tobacco.TobaccoFeedResponse
import model.tobacco.TobaccoVoteRequest
import utils.answer.Answer
import utils.answer.BaseRemoteDataSource

class RemoteMainDataSource(
    private val httpClient: HttpClient
) : BaseRemoteDataSource() {

    suspend fun getTobaccoFeed(token: String): Answer<List<TobaccoFeedResponse>> {
        return apiCall {
            httpClient.get {
                header(HttpHeaders.Authorization, token)
                url("tobaccos")
            }
        }
    }

    suspend fun postTobaccoInfo(token: String, request: TobaccoInfoRequest): Answer<TobaccoInfoResponse> {
        return apiCall {
            httpClient.post {
                header(HttpHeaders.Authorization, token)
                setBody(request)
                url("tobacco_info")
            }
        }
    }

    suspend fun postTobaccoVote(token: String, request: TobaccoVoteRequest): Answer<Unit> {
        return apiCall {
            httpClient.post {
                header(HttpHeaders.Authorization, token)
                setBody(request)
                url("vote_tobacco")
            }
        }
    }
}

package data

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.HttpHeaders
import model.tobacco.TobaccoFeedResponse
import model.tobacco.TobaccoInfoRequest
import model.tobacco.TobaccoInfoResponse
import model.tobacco.TobaccoVoteRequest
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import utils.answer.Answer
import utils.answer.BaseRemoteDataSource

class RemoteMainDataSource(
    private val httpClient: HttpClient
) : KoinComponent, BaseRemoteDataSource() {

    val settings: SettingsDataSource by inject()

    suspend fun getTobaccoFeed(): Answer<List<TobaccoFeedResponse>> {
        return apiCall {
            httpClient.get {
                header(HttpHeaders.Authorization, settings.getToken())
                url("tobaccos")
            }
        }
    }

    suspend fun postTobaccoInfo(request: TobaccoInfoRequest): Answer<TobaccoInfoResponse> {
        return apiCall {
            httpClient.post {
                header(HttpHeaders.Authorization, settings.getToken())
                parameter("userId", settings.getUserId())
                url("tobacco_info")
                setBody(request)
            }
        }
    }

    suspend fun postTobaccoVote(request: TobaccoVoteRequest): Answer<Unit> {
        return apiCall {
            httpClient.post {
                header(HttpHeaders.Authorization, settings.getToken())
                parameter("userId", settings.getUserId())
                url("vote_tobacco")
                setBody(request)
            }
        }
    }
}

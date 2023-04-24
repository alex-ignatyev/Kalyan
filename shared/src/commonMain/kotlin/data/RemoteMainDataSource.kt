package data

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.HttpHeaders
import kotlin.random.Random
import model.Tobacco
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

    suspend fun createTobacco(token: String): Answer<Unit> {
        return apiCall {
            httpClient.post {
                setBody(
                    Tobacco(
                        company = "Test- ${Random.nextInt(0, 10000)}",
                        taste = "test ${Random.nextInt(0, 10000)}",
                        strength = Random.nextInt(0, 10),
                        smokiness = Random.nextInt(0, 10),
                        aroma = Random.nextInt(0, 10)
                    )
                )
                header(HttpHeaders.Authorization, token)
                url("insert_tobacco")
            }
        }
    }
}

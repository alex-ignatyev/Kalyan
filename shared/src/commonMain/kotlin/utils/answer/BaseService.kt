package utils.answer

import utils.answer.ErrorCode.BadRequest
import utils.answer.ErrorCode.Conflict
import utils.answer.ErrorCode.InternalError
import utils.answer.ErrorCode.Unauthorized
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import utils.answer.Answer.Companion

abstract class BaseRemoteDataSource {

    protected suspend inline fun <reified T : Any> apiCall(call: () -> HttpResponse): Answer<T> {
        val response = try {
            call.invoke()
        } catch (e: Exception) {
            return Answer.failure(code = InternalError)
        }

        return when (response.status.value) {
            400 -> Answer.failure(
                code = BadRequest,
                message = response.body()
            )
            401 -> Answer.failure(
                code = Unauthorized,
                message = response.body()
            )
            409 -> Answer.failure(
                code = Conflict,
                message = response.body()
            )
            else -> Answer.success(response.body())
        }
    }
}

package domain.repository

import data.RemoteMainDataSource
import data.SettingsDataSource
import model.tobacco.TobaccoInfoRequest
import model.tobacco.TobaccoInfoResponse
import model.tobacco.TobaccoResponse
import model.tobacco.TobaccoVoteRequest
import utils.answer.Answer

class RatingRepositoryImpl(
    private val remote: RemoteMainDataSource,
    private val settings: SettingsDataSource
) : RatingRepository {

    override suspend fun getTobaccoFeed(): Answer<List<TobaccoResponse>> {
        return remote.getTobaccoFeed(settings.getToken())
    }

    override suspend fun getTobaccoInfo(tobaccoId: String): Answer<TobaccoInfoResponse> {
        return remote.postTobaccoInfo(
            settings.getToken(), TobaccoInfoRequest(
                userId = settings.getUserId(),
                tobaccoId = tobaccoId
            )
        )
    }

    override suspend fun postTobaccoVote(
        tobaccoId: String,
        strength: Int,
        smokiness: Int,
        aroma: Int,
        tastePower: Int,
        rating: Int
    ): Answer<Unit> {
        return remote.postTobaccoVote(
            settings.getToken(), TobaccoVoteRequest(
                userId = settings.getUserId(),
                tobaccoId = tobaccoId,
                strength = strength,
                smokiness = smokiness,
                aroma = aroma,
                tastePower = tastePower,
                rating = rating

            )
        )
    }
}

interface RatingRepository {
    suspend fun getTobaccoFeed(): Answer<List<TobaccoResponse>>
    suspend fun getTobaccoInfo(tobaccoId: String): Answer<TobaccoInfoResponse>
    suspend fun postTobaccoVote(
        tobaccoId: String,
        strength: Int,
        smokiness: Int,
        aroma: Int,
        tastePower: Int,
        rating: Int
    ): Answer<Unit>
}

package domain.repository

import data.RemoteMainDataSource
import data.SettingsDataSource
import model.tobacco.TobaccoInfoRequest
import model.tobacco.TobaccoInfoResponse
import model.tobacco.TobaccoResponse
import model.tobacco.TobaccoVoteRequest
import model.tobacco.TobaccoVoteRequest.VoteType
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
        type: VoteType,
        value: Long
    ): Answer<Unit> {
        return remote.postTobaccoVote(
            settings.getToken(), TobaccoVoteRequest(
                userId = settings.getUserId(),
                tobaccoId = tobaccoId,
                type = type,
                value = value
            )
        )
    }
}

interface RatingRepository {
    suspend fun getTobaccoFeed(): Answer<List<TobaccoResponse>>
    suspend fun getTobaccoInfo(tobaccoId: String): Answer<TobaccoInfoResponse>
    suspend fun postTobaccoVote(
        tobaccoId: String,
        type: VoteType,
        value: Long
    ): Answer<Unit>
}

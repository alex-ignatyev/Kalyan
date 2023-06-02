package domain.repository

import data.RemoteMainDataSource
import model.tobacco.TobaccoFeedResponse
import model.tobacco.TobaccoInfoRequest
import model.tobacco.TobaccoInfoResponse
import model.tobacco.TobaccoVoteRequest
import model.tobacco.TobaccoVoteRequest.VoteType
import utils.answer.Answer

class RatingRepositoryImpl(
    private val remote: RemoteMainDataSource
) : RatingRepository {

    override suspend fun getTobaccoFeed(): Answer<List<TobaccoFeedResponse>> {
        return remote.getTobaccoFeed()
    }

    override suspend fun getTobaccoInfo(tobaccoId: String): Answer<TobaccoInfoResponse> {
        return remote.postTobaccoInfo(TobaccoInfoRequest(tobaccoId))
    }

    override suspend fun postTobaccoVote(
        tobaccoId: String,
        type: VoteType,
        value: Long
    ): Answer<Unit> {
        return remote.postTobaccoVote(
            TobaccoVoteRequest(
                tobaccoId = tobaccoId,
                type = type,
                value = value
            )
        )
    }
}

interface RatingRepository {
    suspend fun getTobaccoFeed(): Answer<List<TobaccoFeedResponse>>
    suspend fun getTobaccoInfo(tobaccoId: String): Answer<TobaccoInfoResponse>
    suspend fun postTobaccoVote(
        tobaccoId: String,
        type: VoteType,
        value: Long
    ): Answer<Unit>
}

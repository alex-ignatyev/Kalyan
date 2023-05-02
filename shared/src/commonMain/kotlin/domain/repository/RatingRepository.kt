package domain.repository

import data.RemoteMainDataSource
import data.SettingsDataSource
import model.tobacco.TobaccoResponse
import utils.answer.Answer

class RatingRepositoryImpl(
    private val remote: RemoteMainDataSource,
    private val settings: SettingsDataSource
) : RatingRepository {

    override suspend fun getTobaccoFeed(): Answer<List<TobaccoResponse>> {
        return remote.getTobaccoFeed(settings.getToken())
    }
}

interface RatingRepository {
    suspend fun getTobaccoFeed(): Answer<List<TobaccoResponse>>
}

package domain.repository

import data.RemoteMainDataSource
import data.SettingsDataSource
import model.Tobaccos
import utils.answer.Answer

class MainRepositoryImpl(
    private val remote: RemoteMainDataSource,
    private val settings: SettingsDataSource
) : MainRepository {

    override suspend fun getTobaccos(): Answer<Tobaccos> {
        return remote.getTobaccos(settings.getToken())
    }

    override suspend fun createTobacco(): Answer<Unit> {
        return remote.createTobacco(settings.getToken())
    }
}

interface MainRepository {
    suspend fun getTobaccos(): Answer<Tobaccos>
    suspend fun createTobacco(): Answer<Unit>
}

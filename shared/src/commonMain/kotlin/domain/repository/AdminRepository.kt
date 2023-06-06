package domain.repository

import data.RemoteAdminDataSource
import data.SettingsDataSource
import model.data.admin.AdminAddTobaccoRequest
import model.domain.Company
import model.domain.toDomain
import utils.answer.Answer
import utils.answer.map

class AdminRepositoryImpl(
    private val remote: RemoteAdminDataSource,
    private val settings: SettingsDataSource
) : AdminRepository {

    override suspend fun addTobacco(request: AdminAddTobaccoRequest): Answer<Unit> {
        return remote.addTobacco(settings.getToken(), request)
    }

    override suspend fun getCompanies(): Answer<List<Company>> {
        return remote.getCompanies(settings.getToken()).map { it.toDomain() }
    }

}

interface AdminRepository {
    suspend fun addTobacco(request: AdminAddTobaccoRequest): Answer<Unit>
    suspend fun getCompanies(): Answer<List<Company>>
}
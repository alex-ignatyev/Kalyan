package domain.repository

import data.RemoteAdminDataSource
import data.SettingsDataSource
import model.admin.AdminAddTobaccoRequest
import model.admin.CompanyResponse
import utils.answer.Answer

class AdminRepositoryImpl(
    private val remote: RemoteAdminDataSource,
    private val settings: SettingsDataSource
) : AdminRepository {

    override suspend fun addTobacco(request: AdminAddTobaccoRequest): Answer<Unit> {
        return remote.addTobacco(settings.getToken(), request)
    }

    override suspend fun getCompanies(): Answer<List<CompanyResponse>> {
       return remote.getCompanies(settings.getToken())
    }

}

interface AdminRepository {
    suspend fun addTobacco(request: AdminAddTobaccoRequest): Answer<Unit>
    suspend fun getCompanies(): Answer<List<CompanyResponse>>
}
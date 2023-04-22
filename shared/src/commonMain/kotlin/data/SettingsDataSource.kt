package data

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get

class SettingsDataSource(
    private val settings: Settings
) {

    fun saveToken(token: String) {
        settings.putString(TOKEN_KEY, "Bearer $token")
    }

    fun getToken(): String {
        return settings[TOKEN_KEY, ""]
    }

    companion object {
        private const val TOKEN_KEY = "tokenKey"
    }
}

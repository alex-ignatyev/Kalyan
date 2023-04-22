package di

import data.features.daily.dailyModuleKoin
import data.features.medication.medicationModuleKoin
import ktor.ktoreModule
import org.koin.core.context.startKoin
import org.koin.dsl.module

object PlatformSDK {

    fun init(
        configuration: PlatformConfiguration
    ) {

        startKoin {
            modules(
                module { single<PlatformConfiguration> { configuration } },
                ktoreModule,
                databaseModule,
                settingsModule,

                authModule,
                mainModule,

                dailyModuleKoin,
                medicationModuleKoin
            )
        }
    }
}

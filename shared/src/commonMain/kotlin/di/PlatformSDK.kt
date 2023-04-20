package di

import data.features.daily.dailyModule
import data.features.daily.dailyModuleKoin
import data.features.medication.medicationModule
import data.features.medication.medicationModuleKoin
import ktor.ktoreModule
import ktor.ktoreModuleKoin
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.direct
import org.kodein.di.singleton
import org.koin.core.context.startKoin
import org.koin.dsl.module

object PlatformSDK {

    fun init(
        configuration: PlatformConfiguration
    ) {

        startKoin {
            modules(
                module { single<PlatformConfiguration> { configuration } },
                serializationModuleKoin,
                databaseModuleKoin,
                dailyModuleKoin,
                medicationModuleKoin,
                ktoreModuleKoin
            )
        }

        /*val umbrellaModule = DI.Module("umbrella") {
            bind<PlatformConfiguration>() with singleton { configuration }
        }

        Inject.createDependencies(
            DI {
                importAll(
                    umbrellaModule,
                    coreModule,
                    dailyModule,
                    medicationModule,
                    ktoreModule
                )
            }.direct
        )*/
    }
}
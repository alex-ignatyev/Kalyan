package di

import org.kodein.di.DI
import org.koin.dsl.module

val coreModule = DI.Module("coreModule") {
    importAll(
        serializationModule,
        databaseModule
    )
}
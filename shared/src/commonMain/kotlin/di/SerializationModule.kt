package di

import kotlinx.serialization.json.Json
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton
import org.koin.dsl.module

val serializationModule = DI.Module("serializationModule") {
    bind<Json>() with singleton {
        Json {
            isLenient = true
            ignoreUnknownKeys = true
        }
    }
}

val serializationModuleKoin = module {
    single {
        Json {
            isLenient = true
            ignoreUnknownKeys = true
        }
    }
}
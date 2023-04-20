package data.features.daily

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider
import org.koin.dsl.module

val dailyModule = DI.Module("daily") {
    bind<DailyRepository>() with provider {
        DailyRepository(instance())
    }
}

val dailyModuleKoin = module {
    single { DailyRepository(get()) }
}
package data.features.daily

import org.koin.dsl.module

val dailyModuleKoin = module {
    single { DailyRepository(get()) }
}

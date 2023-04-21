package di

import Database
import database.DriverFactory
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import org.koin.dsl.module

val databaseModule = DI.Module("databaseModule") {
    bind<DriverFactory>() with singleton {
        DriverFactory(instance())
    }

    bind<Database>() with singleton {
        val driverFactory: DriverFactory = instance()
        val driver = driverFactory
            .createDriver("lm_customer.db")

        Database(driver)
    }
}

val databaseModuleKoin = module {

    single { DriverFactory(get()) }

    single {
        val driverFactory: DriverFactory = get()
        val driver = driverFactory
            .createDriver("lm_customer.db")

        Database(driver)
    }
}
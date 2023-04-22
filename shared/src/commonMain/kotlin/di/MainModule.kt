package di

import data.RemoteMainDataSource
import domain.repository.MainRepository
import domain.repository.MainRepositoryImpl
import org.koin.dsl.module

val mainModule = module {
    single { RemoteMainDataSource(get()) }
    single<MainRepository> { MainRepositoryImpl(get(), get()) }
}

package data.features.medication

import org.koin.dsl.module

val medicationModuleKoin = module {
    single { MedicationRepository(get()) }
}

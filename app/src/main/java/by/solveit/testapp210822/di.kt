package by.solveit.testapp210822

import by.solveit.testapp210822.data.dataModule
import by.solveit.testapp210822.domain.domainModule
import by.solveit.testapp210822.presentation.presentationModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.dsl.module

@ExperimentalCoroutinesApi
@ExperimentalSerializationApi
val appModule = module {
    single { AppDispatchers() }
} + dataModule + domainModule + presentationModule

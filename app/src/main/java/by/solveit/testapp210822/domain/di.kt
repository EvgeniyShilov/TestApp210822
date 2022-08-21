package by.solveit.testapp210822.domain

import by.solveit.testapp210822.domain.interactors.DetailsInteractor
import by.solveit.testapp210822.domain.interactors.PreviewInteractor
import org.koin.dsl.module

val domainModule = module {

    single { PreviewInteractor(get(), get()) }

    single { DetailsInteractor(get(), get()) }
}

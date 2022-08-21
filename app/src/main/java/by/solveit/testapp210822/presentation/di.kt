package by.solveit.testapp210822.presentation

import by.solveit.testapp210822.presentation.details.DetailsViewModel
import by.solveit.testapp210822.presentation.preview.PreviewViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val presentationModule = module {

    viewModel { PreviewViewModel(get(), get()) }

    viewModel { (userId: Long) -> DetailsViewModel(userId, get(), get()) }
}

package by.solveit.testapp210822.domain.interactors

import by.solveit.testapp210822.AppDispatchers
import by.solveit.testapp210822.domain.repositories.DataRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class PreviewInteractor(
    private val repository: DataRepository,
    private val dispatchers: AppDispatchers
) {

    @ExperimentalCoroutinesApi
    fun userPreviews() = repository.userPreviews(10)
        .flowOn(dispatchers.io)

    suspend fun refreshData() = withContext(dispatchers.io) {
        repository.refreshData()
    }
}

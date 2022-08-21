package by.solveit.testapp210822.domain.interactors

import by.solveit.testapp210822.AppDispatchers
import by.solveit.testapp210822.domain.repositories.DataRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class DetailsInteractor(
    private val repository: DataRepository,
    private val dispatchers: AppDispatchers
) {

    @ExperimentalCoroutinesApi
    fun userDetails(
        userId: Long
    ) = repository.userDetails(userId)
        .flowOn(dispatchers.io)

    suspend fun refreshData() = withContext(dispatchers.io) {
        repository.refreshData()
    }
}

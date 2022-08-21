package by.solveit.testapp210822.domain.repositories

import by.solveit.testapp210822.domain.models.UserDetails
import by.solveit.testapp210822.domain.models.UserPreview
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

interface DataRepository {

    @ExperimentalCoroutinesApi
    fun userPreviews(
        limit: Int = Int.MAX_VALUE
    ): Flow<List<UserPreview>>

    @ExperimentalCoroutinesApi
    fun userDetails(
        userId: Long
    ): Flow<UserDetails?>

    suspend fun refreshData()
}

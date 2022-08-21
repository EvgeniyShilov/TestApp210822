package by.solveit.testapp210822.data.repositories

import by.solveit.testapp210822.data.local.DAO
import by.solveit.testapp210822.data.local.UserPreviewData
import by.solveit.testapp210822.data.remote.API
import by.solveit.testapp210822.data.remote.PostDTO
import by.solveit.testapp210822.data.remote.UserDTO
import by.solveit.testapp210822.domain.repositories.DataRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.mapLatest

class DataRepositoryImpl(
    private val api: API,
    private val dao: DAO
) : DataRepository {

    @ExperimentalCoroutinesApi
    override fun userPreviews(
        limit: Int
    ) = dao.userPreviewData(limit)
        .mapLatest { list -> list.map(UserPreviewData::toUserPreview) }

    @ExperimentalCoroutinesApi
    override fun userDetails(
        userId: Long
    ) = dao.userDetailsData(userId)
        .mapLatest { list -> list.singleOrNull()?.toUserDetails() }

    override suspend fun refreshData() = coroutineScope {
        val getUsers = async { api.users() }
        val getPosts = async { api.posts() }
        val users = getUsers.await().map(UserDTO::toUserEntity)
        val posts = getPosts.await().map(PostDTO::toPostEntity)
        dao.insertAll(users, posts)
    }
}

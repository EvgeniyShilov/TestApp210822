package by.solveit.testapp210822.data.remote

import by.solveit.testapp210822.data.local.PostEntity
import kotlinx.serialization.Serializable

@Serializable
data class PostDTO(
    val id: Long,
    val userId: Long,
    val title: String,
    val body: String
) {

    fun toPostEntity() = PostEntity(
        id = id,
        authorId = userId,
        title = title,
        body = body
    )
}

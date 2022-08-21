package by.solveit.testapp210822.data.remote

import by.solveit.testapp210822.data.local.UserEntity
import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(
    val userId: Long,
    val albumId: Long,
    val name: String,
    val url: String,
    val thumbnailUrl: String
) {

    fun toUserEntity() = UserEntity(
        id = userId,
        albumId = albumId,
        name = name,
        avatarUrl = url,
        thumbnailUrl = thumbnailUrl
    )
}

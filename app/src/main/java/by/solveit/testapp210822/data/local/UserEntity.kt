package by.solveit.testapp210822.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import by.solveit.testapp210822.domain.models.User

@Entity
data class UserEntity(
    @PrimaryKey
    val id: Long,
    val albumId: Long,
    val name: String,
    val avatarUrl: String,
    val thumbnailUrl: String
) {

    fun toUser() = User(
        id = id,
        name = name,
        avatarUrl = avatarUrl,
        thumbnailUrl = thumbnailUrl
    )
}

package by.solveit.testapp210822.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import by.solveit.testapp210822.domain.models.Post

@Entity
data class PostEntity(
    @PrimaryKey
    val id: Long,
    val authorId: Long,
    val title: String,
    val body: String
) {

    fun toPost() = Post(
        id = id,
        authorId = authorId,
        title = title,
        body = body
    )
}

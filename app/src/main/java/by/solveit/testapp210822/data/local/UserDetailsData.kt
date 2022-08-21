package by.solveit.testapp210822.data.local

import androidx.room.Embedded
import androidx.room.Relation
import by.solveit.testapp210822.domain.models.UserDetails

data class UserDetailsData(
    @Embedded
    val user: UserEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "authorId"
    )
    val posts: List<PostEntity>
) {

    fun toUserDetails() = UserDetails(
        user = user.toUser(),
        posts = posts.map(PostEntity::toPost)
    )
}

package by.solveit.testapp210822.data.local

import androidx.room.DatabaseView
import by.solveit.testapp210822.domain.models.UserPreview

@DatabaseView(
    """
        SELECT
        u.id AS userId,
        u.name AS userName,
        u.thumbnailUrl AS userAvatarUrl,
        COUNT(p.id) AS postCount
        FROM UserEntity AS u LEFT JOIN PostEntity AS p ON u.id = p.authorId
        GROUP BY u.id
    """
)
data class UserPreviewData(
    val userId: Long,
    val userName: String,
    val userAvatarUrl: String,
    val postCount: Int
) {

    fun toUserPreview() = UserPreview(
        userId = userId,
        userName = userName,
        userAvatarUrl = userAvatarUrl,
        postCount = postCount
    )
}

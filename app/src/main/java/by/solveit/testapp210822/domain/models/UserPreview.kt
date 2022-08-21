package by.solveit.testapp210822.domain.models

data class UserPreview(
    val userId: Long,
    val userName: String,
    val userAvatarUrl: String,
    val postCount: Int
)

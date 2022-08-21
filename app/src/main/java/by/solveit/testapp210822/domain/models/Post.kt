package by.solveit.testapp210822.domain.models

data class Post(
    val id: Long,
    val authorId: Long,
    val title: String,
    val body: String
)

package by.solveit.testapp210822.presentation.details

import by.solveit.testapp210822.domain.models.Post

sealed class DetailsItem {

    data class HeaderItem(
        val userAvatarUrl: String
    ) : DetailsItem()

    data class PostItem(
        val post: Post
    ) : DetailsItem()
}

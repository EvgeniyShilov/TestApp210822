package by.solveit.testapp210822.presentation.preview

import by.solveit.testapp210822.domain.models.UserPreview

data class PreviewState(
    val data: List<UserPreview>? = null,
    val isInProgress: Boolean = false,
    val error: String? = null,
    val clickedItem: UserPreview? = null
)

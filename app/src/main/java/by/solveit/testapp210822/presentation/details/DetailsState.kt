package by.solveit.testapp210822.presentation.details

data class DetailsState(
    val data: List<DetailsItem>? = null,
    val isInProgress: Boolean = false,
    val error: String? = null
)

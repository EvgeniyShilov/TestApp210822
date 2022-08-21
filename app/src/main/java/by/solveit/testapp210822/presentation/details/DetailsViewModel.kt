package by.solveit.testapp210822.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.solveit.testapp210822.AppDispatchers
import by.solveit.testapp210822.domain.interactors.DetailsInteractor
import by.solveit.testapp210822.domain.models.UserDetails
import by.solveit.testapp210822.presentation.details.DetailsItem.HeaderItem
import by.solveit.testapp210822.presentation.details.DetailsItem.PostItem
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalCoroutinesApi
class DetailsViewModel(
    userId: Long,
    private val interactor: DetailsInteractor,
    private val dispatchers: AppDispatchers
) : ViewModel() {

    private val innerStateLiveData = MutableLiveData(DetailsState())
    val stateLiveData: LiveData<DetailsState> = innerStateLiveData

    init {
        interactor.userDetails(userId)
            .onEach { modifyState { copy(data = it?.toDetailsItems()) } }
            .flowOn(dispatchers.main)
            .launchIn(viewModelScope)
        refreshData()
    }

    fun onRefresh() = refreshData()

    private fun UserDetails.toDetailsItems() = posts.map { PostItem(it) as DetailsItem }
        .toMutableList()
        .apply { add(0, HeaderItem(user.avatarUrl)) }
        .toList()

    private fun refreshData() {
        viewModelScope.launch(dispatchers.main) {
            try {
                modifyState { copy(isInProgress = true) }
                interactor.refreshData()
                modifyState { copy(error = null) }
            } catch (ignored: CancellationException) {
                throw ignored
            } catch (throwable: Throwable) {
                modifyState { copy(error = throwable.message) }
            } finally {
                withContext(NonCancellable) {
                    modifyState { copy(isInProgress = false) }
                }
            }
        }
    }

    private fun modifyState(
        modifier: DetailsState.() -> DetailsState
    ) {
        innerStateLiveData.value = modifier(innerStateLiveData.value!!)
    }
}

package by.solveit.testapp210822.presentation.preview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.solveit.testapp210822.AppDispatchers
import by.solveit.testapp210822.domain.interactors.PreviewInteractor
import by.solveit.testapp210822.domain.models.UserPreview
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalCoroutinesApi
class PreviewViewModel(
    private val interactor: PreviewInteractor,
    private val dispatchers: AppDispatchers
) : ViewModel() {

    private val innerStateLiveData = MutableLiveData(PreviewState())
    val stateLiveData: LiveData<PreviewState> = innerStateLiveData

    init {
        interactor.userPreviews()
            .onEach { modifyState { copy(data = it) } }
            .flowOn(dispatchers.main)
            .launchIn(viewModelScope)
        refreshData()
    }

    fun onRefresh() = refreshData()

    fun onItemClick(
        item: UserPreview
    ) = modifyState { copy(clickedItem = item) }

    fun onClickConsumed() = modifyState { copy(clickedItem = null) }

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
        modifier: PreviewState.() -> PreviewState
    ) {
        innerStateLiveData.value = modifier(innerStateLiveData.value!!)
    }
}

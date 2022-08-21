package by.solveit.testapp210822.presentation.preview

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import by.solveit.testapp210822.R
import by.solveit.testapp210822.databinding.FragmentPreviewBinding
import by.solveit.testapp210822.presentation.details.DetailsFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel


@ExperimentalCoroutinesApi
class PreviewFragment : Fragment(R.layout.fragment_preview) {

    private val binding by viewBinding(FragmentPreviewBinding::bind)
    private val model by viewModel<PreviewViewModel>()
    private val adapter by lazy { PreviewAdapter(model::onItemClick) }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) = with(binding) {
        model.stateLiveData.observe(viewLifecycleOwner) { state ->
            with(binding) {
                adapter.submitList(state.data)
                vRefresher.isRefreshing = state.isInProgress
                vError.isVisible = state.error != null
                vError.text = state.error
                if (state.clickedItem != null) {
                    parentFragmentManager.beginTransaction()
                        .replace(
                            R.id.vContainer,
                            DetailsFragment.newInstance(state.clickedItem.userId)
                        ).addToBackStack(null)
                        .commit()
                    model.onClickConsumed()
                }
            }
        }
        vRefresher.setOnRefreshListener(model::onRefresh)
        vList.layoutManager = LinearLayoutManager(requireContext())
        vList.adapter = adapter
    }

    companion object {

        fun newInstance() = PreviewFragment()
    }
}

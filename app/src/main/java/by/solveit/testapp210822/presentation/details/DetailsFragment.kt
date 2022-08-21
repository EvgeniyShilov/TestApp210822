package by.solveit.testapp210822.presentation.details

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import by.solveit.testapp210822.R
import by.solveit.testapp210822.databinding.FragmentDetailsBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


@ExperimentalCoroutinesApi
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val binding by viewBinding(FragmentDetailsBinding::bind)
    private val model by viewModel<DetailsViewModel> { parametersOf(arguments?.get(KEY_USER_ID)) }
    private val adapter by lazy { DetailsAdapter() }

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
            }
        }
        vRefresher.setOnRefreshListener(model::onRefresh)
        vList.layoutManager = LinearLayoutManager(requireContext())
        vList.adapter = adapter
    }

    companion object {

        private const val KEY_USER_ID = "user_id"

        fun newInstance(
            userId: Long
        ) = DetailsFragment().apply {
            arguments = bundleOf(KEY_USER_ID to userId)
        }
    }
}

package by.godevelopment.currencyexchangeapplication.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.flowWithLifecycle
import by.godevelopment.currencyexchangeapplication.R
import by.godevelopment.currencyexchangeapplication.appComponent
import by.godevelopment.currencyexchangeapplication.databinding.FragmentMainBinding
import by.godevelopment.currencyexchangeapplication.ui.adapters.CurrencyAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MainFragment : Fragment() {

    companion object {
        const val SCROLL_DELAY = 500L
        fun newInstance() = MainFragment()
    }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val adapter: CurrencyAdapter by lazy {
        CurrencyAdapter(
            onClickItem = ::onClickItemAction,
            onTextChange = viewModel::onTextChange,
        ).also {
            binding.recyclerView.adapter = it
        }
    }

    @Inject
    lateinit var factory: MainViewModel.Factory
    private val viewModel: MainViewModel by viewModels { factory }


    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        setupUi()
        setupEvent()
        return binding.root
    }

    private fun setupUi() = with(binding) {
            viewModel.uiState
                .flowWithLifecycle(lifecycle)
                .onEach { uiState ->
                    if (!uiState.isFetchingData) progressBar.visibility = View.GONE
                    else progressBar.visibility = View.VISIBLE
                    adapter.submitList(uiState.dataList)
                    adapter.topItemHasFocus = uiState.topHasFocus
                }
                .launchIn(lifecycle.coroutineScope)
        }

    private fun setupEvent() {
        viewModel.uiEvent
            .flowWithLifecycle(lifecycle)
            .onEach { event ->
                Snackbar
                    .make(
                        binding.root,
                        event,
                        Snackbar.LENGTH_INDEFINITE,
                    )
                    .setAction(R.string.snackbar_reload) { viewModel.reloadDataList() }
                    .show()
            }
            .launchIn(lifecycle.coroutineScope)
    }

    private fun onClickItemAction(itemBase: String) {
        viewModel.onClickItem(itemBase)
        with(binding) {
            recyclerView.postDelayed(
                Runnable { recyclerView.smoothScrollToPosition(0) },
                SCROLL_DELAY
            )
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}

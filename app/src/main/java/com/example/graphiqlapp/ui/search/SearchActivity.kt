package com.example.graphiqlapp.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.graphiqlapp.R
import com.example.graphiqlapp.databinding.ActivitySearchBinding
import com.example.graphiqlapp.ui.common.collectFlow
import com.example.graphiqlapp.ui.common.onClickEvents
import com.example.graphiqlapp.ui.common.visible
import dagger.android.AndroidInjection
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class SearchActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: SearchViewModel by viewModels { viewModelFactory }

    lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val stationsAdapter = StationsAdapter(lifecycleScope)

        lifecycleScope.collectFlow(viewModel.spinner) { binding.progress.visible = it }

        binding.recycler.adapter = stationsAdapter

        lifecycleScope.collectFlow(viewModel.searchUiState) { searchUiState ->

            when(searchUiState) {
                is SearchViewModel.SearchUiState.Error -> {
                    binding.errorMessage.text = searchUiState.message
                    binding.errorMessage.visible = true
                    binding.recycler.visible = false
                }
                is SearchViewModel.SearchUiState.Success -> {

                    binding.errorMessage.visible = false
                    binding.recycler.visible = true
                    stationsAdapter.submitList(searchUiState.listStations)
                }
                else -> {
                    binding.errorMessage.text = getString(R.string.no_station_found)
                    binding.recycler.visible = false
                    binding.errorMessage.visible = true
                }
            }
        }


        lifecycleScope.collectFlow(binding.buttonSearch.onClickEvents) {

            viewModel.search(binding.input.text.toString())
        }
    }
}
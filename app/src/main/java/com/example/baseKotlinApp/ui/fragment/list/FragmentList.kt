package com.example.baseKotlinApp.ui.fragment.list

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.baseKotlinApp.base.BaseFragment
import com.example.baseKotlinApp.databinding.FragmentListBinding
import com.example.baseKotlinApp.model.MakeUpItem
import com.example.baseKotlinApp.utils.ApiState
import com.example.baseKotlinApp.utils.visibleIf
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class FragmentList : BaseFragment<FragmentListBinding, ListVM>() {
    override val viewModel: ListVM by viewModels()

    override fun getViewBinding() = FragmentListBinding.inflate(layoutInflater)

    private val makeUpListAdapter = MakeUpListAdapter(::onClickMakeUpAction)

    override fun onFragmentCreated() {
        binding.rvMakeUp.adapter = makeUpListAdapter
    }

    override fun observe() {

        lifecycleScope.launchWhenResumed {

            viewModel.onMakeUpList.collect {
                when (it) {
                    ApiState.Empty -> {}

                    ApiState.Loading -> {
                        viewModel.loadingDetection.postValue(true)
                    }

                    is ApiState.Failure -> {
                        binding.apply {
                            rvMakeUp.visibleIf(false)
                            txtNoRecord.visibleIf(true)
                        }
                    }

                    is ApiState.Success -> {
                        binding.apply {
                            txtNoRecord.visibleIf(false)
                            rvMakeUp.visibleIf(true)
                        }
                        makeUpListAdapter.replaceData(it.data)
                    }
                }
            }
        }
    }

    private fun onClickMakeUpAction(makeUp: MakeUpItem) {
        viewModel.goToMakeUpDetail(makeUp)
    }
}
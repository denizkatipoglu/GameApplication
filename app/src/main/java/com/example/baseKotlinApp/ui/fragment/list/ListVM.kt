package com.example.baseKotlinApp.ui.fragment.list

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.example.baseKotlinApp.R
import com.example.baseKotlinApp.base.BaseViewModel
import com.example.baseKotlinApp.model.MakeUpItem
import com.example.baseKotlinApp.repository.MakeUpRepository
import com.example.baseKotlinApp.utils.ApiState
import com.example.baseKotlinApp.utils.MAKEUP_ITEM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListVM @Inject constructor(
    myApp: Application,
    private val carsRepository: MakeUpRepository
) : BaseViewModel(app = myApp) {

    private val _onMakeUpList = MutableStateFlow<ApiState<List<MakeUpItem>?>>(ApiState.Empty)
    val onMakeUpList: StateFlow<ApiState<List<MakeUpItem>?>> = _onMakeUpList

    init {
        getMakeUp()
    }

    private fun getMakeUp() = viewModelScope.launch {
        _onMakeUpList.value = ApiState.Loading
        carsRepository.getMakeUps(
            scope = viewModelScope,
            onSuccess = {
                loadingDetection.postValue(false)
                _onMakeUpList.value = ApiState.Success(it)
            }, onErrorAction = {
                loadingDetection.postValue(false)
                _onMakeUpList.value = ApiState.Failure(it)
            })
    }

    fun goToMakeUpDetail(makeUp: MakeUpItem) {
        Bundle().apply {
            putSerializable(MAKEUP_ITEM, makeUp)
            navigateFragment(R.id.action_global_fragmentDetail, this)
        }
    }
}
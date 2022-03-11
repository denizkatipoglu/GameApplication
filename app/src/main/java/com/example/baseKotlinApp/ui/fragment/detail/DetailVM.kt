package com.example.baseKotlinApp.ui.fragment.detail

import android.app.Application
import com.example.baseKotlinApp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailVM @Inject constructor(
    myApp: Application
) : BaseViewModel(app = myApp)
package com.example.baseKotlinApp.ui.activity

import android.app.Application
import com.example.baseKotlinApp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SingleVM
@Inject constructor(myApp: Application) : BaseViewModel(app = myApp)
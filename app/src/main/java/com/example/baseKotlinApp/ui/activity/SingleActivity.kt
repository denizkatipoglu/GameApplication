package com.example.baseKotlinApp.ui.activity

import android.content.Intent
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.baseKotlinApp.R
import com.example.baseKotlinApp.base.BaseActivity
import com.example.baseKotlinApp.databinding.ActivitySingleBinding
import com.example.baseKotlinApp.utils.NavigateFragmentParams
import com.example.baseKotlinApp.utils.visibleIf
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingleActivity : BaseActivity<ActivitySingleBinding, SingleVM>(){

    override val viewModel: SingleVM by viewModels()
    override fun getViewBinding() = ActivitySingleBinding.inflate(layoutInflater)

    private var currentNavController: NavController? = null


    override fun onActivityCreated() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.sectionMain)
        currentNavController = (navHostFragment as NavHostFragment).navController
    }

    override fun supportNavigateUpTo(upIntent: Intent) {
        currentNavController?.navigateUp()
    }

    override fun observe() {}

    override fun navigateFragment(params: NavigateFragmentParams) {
        currentNavController?.navigate(
            params.navAction,
            params.bundle,
            params.navOptions,
            params.extras
        )
    }

    override fun showHideProgress(isShow: Boolean) {
        binding.pbLoading.visibleIf(isShow)

    }

}
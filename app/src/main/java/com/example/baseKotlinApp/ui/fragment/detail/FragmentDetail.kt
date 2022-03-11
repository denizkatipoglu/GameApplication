package com.example.baseKotlinApp.ui.fragment.detail

import androidx.fragment.app.viewModels
import com.example.baseKotlinApp.R
import com.example.baseKotlinApp.base.BaseFragment
import com.example.baseKotlinApp.databinding.FragmentDetailBinding
import com.example.baseKotlinApp.model.MakeUpItem
import com.example.baseKotlinApp.utils.MAKEUP_ITEM
import com.example.baseKotlinApp.utils.loadImagesWithGlide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentDetail : BaseFragment<FragmentDetailBinding, DetailVM>() {
    override val viewModel: DetailVM by viewModels()

    override fun getViewBinding() = FragmentDetailBinding.inflate(layoutInflater)

    override fun onFragmentCreated() {
        val makeUp = arguments?.getSerializable(MAKEUP_ITEM) as MakeUpItem?

        binding.apply {
            makeUp?.let {
                imgMakeUp.loadImagesWithGlide("https://"+it.imageUrl)
                txtBrandContent.text = it.brandName
                txtModelContent.text = it.modelName
                txtProductContent.text = it.productType
                txtYearContent.text = it.modelYear.take(4)
                txtPriceContent.text = getString(R.string.price_value, it.price)
            }
        }
    }
}
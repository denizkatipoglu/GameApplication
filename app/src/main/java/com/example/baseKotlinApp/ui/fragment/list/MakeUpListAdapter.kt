package com.example.baseKotlinApp.ui.fragment.list

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.baseKotlinApp.base.BaseAdapter
import com.example.baseKotlinApp.base.BaseHolder
import com.example.baseKotlinApp.databinding.ItemMakeupBinding
import com.example.baseKotlinApp.model.MakeUpItem

class MakeUpListAdapter(private val onClickAction: ((MakeUpItem) -> Unit)) :
    BaseAdapter<MakeUpItem, ItemMakeupBinding, MakeUpListHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MakeUpListHolder {
        return MakeUpListHolder(
            ItemMakeupBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClickAction
        )
    }
}

class MakeUpListHolder(
    viewBinding: ItemMakeupBinding,
    private val onClickAction: ((MakeUpItem) -> Unit)
) :
    BaseHolder<MakeUpItem, ItemMakeupBinding>(viewBinding) {
    override fun bind(binding: ItemMakeupBinding, item: MakeUpItem?) {
        item?.let { car ->
            binding.apply {

                txtBrand.text = car.brandName
                txtMakeUpModel.text = car.modelName

                cvParent.setOnClickListener {
                    onClickAction.invoke(car)
                }
            }
        } ?: return
    }
}
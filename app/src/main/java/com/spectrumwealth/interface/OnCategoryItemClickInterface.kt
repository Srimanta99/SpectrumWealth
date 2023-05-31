package com.spectrumwealth.`interface`

import com.spectrumwealth.model.CategoryApiResponseModel

interface OnCategoryItemClickInterface {
    fun onItemClick(serviceItemData: CategoryApiResponseModel.CategoryItem, position: Int)
}
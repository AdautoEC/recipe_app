package com.k4tr1n4.mlteste.products.domain.use_case

import androidx.paging.PagingData
import androidx.paging.map
import com.k4tr1n4.mlteste.products.data.remote.model.ItemModel.DataModel.ResultModel.ThumbnailModel
import com.k4tr1n4.mlteste.products.domain.data_source.ItemDataSourceFactory
import com.k4tr1n4.mlteste.products.domain.model.MLItemModel
import com.k4tr1n4.mlteste.products.domain.model.MLItemModel.Details
import com.k4tr1n4.mlteste.products.domain.model.MLItemModel.Details.MoreDetails
import com.k4tr1n4.mlteste.products.domain.use_case.GetItemsUseCase.Companion.HTTPS
import com.k4tr1n4.mlteste.products.domain.use_case.GetItemsUseCase.Companion.HTTP
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class GetItemsUseCase @Inject constructor(
    private val dataSource: ItemDataSourceFactory,
) {
    companion object{
        const val DEFAULT_DESCRIPTION = ""
        const val HTTP = "http://"
        const val HTTPS = "https://"
    }
    operator fun invoke(search: String?): Flow<PagingData<MLItemModel>> {
        return dataSource.getItems(search).mapLatest { pagingData ->
            pagingData.map { result ->
                MLItemModel(
                    title = result.title,
                    price = result.price,
                    pathImg = result.thumbnail.addHTTPS(),
                    details = Details(
                        title = result.title,
                        price = result.price,
                        pathImg = result.thumbnail.addHTTPS(),
                        permalink = result.permalink,
                        condition = result.condition,
                        availableQuantity = result.availableQuantity,
                        moreDetails = result.attributes.mapNotNull { moreDetails ->
                            moreDetails.valueName?.let { value ->
                                MoreDetails(
                                    name = moreDetails.name,
                                    value = value
                                )
                            }
                       },
                    )
                )
            }
        }
    }
}

fun String.addHTTPS() = this.replace(HTTP, HTTPS)
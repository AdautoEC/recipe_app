package com.k4tr1n4.mlteste.products.data.remote.model


import com.google.gson.annotations.SerializedName

data class SearchItemModel(
    @SerializedName("available_filters")
    val availableFilters: List<AvailableFilter>,
    @SerializedName("available_sorts")
    val availableSorts: List<AvailableSort>,
    @SerializedName("country_default_time_zone")
    val countryDefaultTimeZone: String,
    @SerializedName("filters")
    val filters: List<Filter>,
    @SerializedName("paging")
    val paging: Paging,
    @SerializedName("pdp_tracking")
    val pdpTracking: PdpTracking,
    @SerializedName("query")
    val query: String,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("site_id")
    val siteId: String,
    @SerializedName("sort")
    val sort: Sort
) {
    data class AvailableFilter(
        @SerializedName("id")
        val id: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("values")
        val values: List<Value>
    ) {
        data class Value(
            @SerializedName("id")
            val id: String,
            @SerializedName("name")
            val name: String,
            @SerializedName("results")
            val results: Int
        )
    }

    data class AvailableSort(
        @SerializedName("id")
        val id: String,
        @SerializedName("name")
        val name: String
    )

    data class Filter(
        @SerializedName("id")
        val id: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("values")
        val values: List<Value>
    ) {
        data class Value(
            @SerializedName("id")
            val id: String,
            @SerializedName("name")
            val name: String,
            @SerializedName("path_from_root")
            val pathFromRoot: List<PathFromRoot>
        ) {
            data class PathFromRoot(
                @SerializedName("id")
                val id: String,
                @SerializedName("name")
                val name: String
            )
        }
    }

    data class Paging(
        @SerializedName("limit")
        val limit: Int,
        @SerializedName("offset")
        val offset: Int,
        @SerializedName("primary_results")
        val primaryResults: Int,
        @SerializedName("total")
        val total: Int
    )

    data class PdpTracking(
        @SerializedName("group")
        val group: Boolean,
        @SerializedName("product_info")
        val productInfo: List<ProductInfo>
    ) {
        data class ProductInfo(
            @SerializedName("id")
            val id: String,
            @SerializedName("score")
            val score: Int,
            @SerializedName("status")
            val status: String
        )
    }

    data class Result(
        @SerializedName("accepts_mercadopago")
        val acceptsMercadopago: Boolean,
        @SerializedName("attributes")
        val attributes: List<Attribute>,
        @SerializedName("available_quantity")
        val availableQuantity: Int,
        @SerializedName("buying_mode")
        val buyingMode: String,
        @SerializedName("catalog_listing")
        val catalogListing: Boolean,
        @SerializedName("catalog_product_id")
        val catalogProductId: String?,
        @SerializedName("category_id")
        val categoryId: String,
        @SerializedName("condition")
        val condition: String,
        @SerializedName("currency_id")
        val currencyId: String,
        @SerializedName("differential_pricing")
        val differentialPricing: DifferentialPricing?,
        @SerializedName("discounts")
        val discounts: Any?,
        @SerializedName("domain_id")
        val domainId: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("installments")
        val installments: Installments,
        @SerializedName("inventory_id")
        val inventoryId: String?,
        @SerializedName("listing_type_id")
        val listingTypeId: String,
        @SerializedName("official_store_id")
        val officialStoreId: Int?,
        @SerializedName("official_store_name")
        val officialStoreName: String?,
        @SerializedName("order_backend")
        val orderBackend: Int,
        @SerializedName("original_price")
        val originalPrice: Double?,
        @SerializedName("permalink")
        val permalink: String,
        @SerializedName("price")
        val price: Double,
        @SerializedName("promotions")
        val promotions: List<Any>,
        @SerializedName("sale_price")
        val salePrice: Any?,
        @SerializedName("seller")
        val seller: Seller,
        @SerializedName("shipping")
        val shipping: Shipping,
        @SerializedName("site_id")
        val siteId: String,
        @SerializedName("stop_time")
        val stopTime: String,
        @SerializedName("thumbnail")
        val thumbnail: String,
        @SerializedName("thumbnail_id")
        val thumbnailId: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("use_thumbnail_id")
        val useThumbnailId: Boolean,
        @SerializedName("winner_item_id")
        val winnerItemId: Any?
    ) {
        data class Attribute(
            @SerializedName("attribute_group_id")
            val attributeGroupId: String,
            @SerializedName("attribute_group_name")
            val attributeGroupName: String,
            @SerializedName("id")
            val id: String,
            @SerializedName("name")
            val name: String,
            @SerializedName("source")
            val source: Long,
            @SerializedName("value_id")
            val valueId: String?,
            @SerializedName("value_name")
            val valueName: String?,
            @SerializedName("value_struct")
            val valueStruct: ValueStruct?,
            @SerializedName("value_type")
            val valueType: String,
            @SerializedName("values")
            val values: List<Value>
        ) {
            data class ValueStruct(
                @SerializedName("number")
                val number: Double,
                @SerializedName("unit")
                val unit: String
            )

            data class Value(
                @SerializedName("id")
                val id: String?,
                @SerializedName("name")
                val name: String?,
                @SerializedName("source")
                val source: Long,
                @SerializedName("struct")
                val struct: Struct?
            ) {
                data class Struct(
                    @SerializedName("number")
                    val number: Double,
                    @SerializedName("unit")
                    val unit: String
                )
            }
        }

        data class DifferentialPricing(
            @SerializedName("id")
            val id: Int
        )

        data class Installments(
            @SerializedName("amount")
            val amount: Double,
            @SerializedName("currency_id")
            val currencyId: String,
            @SerializedName("quantity")
            val quantity: Int,
            @SerializedName("rate")
            val rate: Double
        )

        data class Seller(
            @SerializedName("id")
            val id: Int,
            @SerializedName("nickname")
            val nickname: String
        )

        data class Shipping(
            @SerializedName("benefits")
            val benefits: Any?,
            @SerializedName("free_shipping")
            val freeShipping: Boolean,
            @SerializedName("logistic_type")
            val logisticType: String,
            @SerializedName("mode")
            val mode: String,
            @SerializedName("promise")
            val promise: Any?,
            @SerializedName("store_pick_up")
            val storePickUp: Boolean,
            @SerializedName("tags")
            val tags: List<String>
        )
    }

    data class Sort(
        @SerializedName("id")
        val id: String,
        @SerializedName("name")
        val name: String
    )
}
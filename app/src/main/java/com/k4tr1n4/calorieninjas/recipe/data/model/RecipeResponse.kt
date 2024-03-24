package com.k4tr1n4.calorieninjas.recipe.data.model

import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("from")
    val from: Int,
    @SerializedName("hits")
    val hits: List<Hit>,
    @SerializedName("_links")
    val links: Links,
    @SerializedName("to")
    val to: Int
) {
    data class Hit(
        @SerializedName("_links")
        val links: Links,
        @SerializedName("recipe")
        val recipe: Recipe
    ) {
        data class Links(
            @SerializedName("next")
            val next: Next,
            @SerializedName("self")
            val self: Self
        ) {
            data class Next(
                @SerializedName("href")
                val href: String,
                @SerializedName("title")
                val title: String
            )

            data class Self(
                @SerializedName("href")
                val href: String,
                @SerializedName("title")
                val title: String
            )
        }

        data class Recipe(
            @SerializedName("calories")
            val calories: Double,
            @SerializedName("cautions")
            val cautions: List<String>,
            @SerializedName("co2EmissionsClass")
            val co2EmissionsClass: String,
            @SerializedName("cuisineType")
            val cuisineType: List<String>,
            @SerializedName("dietLabels")
            val dietLabels: List<String>,
            @SerializedName("digest")
            val digest: List<Digest>,
            @SerializedName("dishType")
            val dishType: List<String>,
            @SerializedName("externalId")
            val externalId: String,
            @SerializedName("glycemicIndex")
            val glycemicIndex: Int,
            @SerializedName("healthLabels")
            val healthLabels: List<String>,
            @SerializedName("image")
            val image: String,
            @SerializedName("images")
            val images: Images,
            @SerializedName("inflammatoryIndex")
            val inflammatoryIndex: Int,
            @SerializedName("ingredientLines")
            val ingredientLines: List<String>,
            @SerializedName("ingredients")
            val ingredients: List<Ingredient>,
            @SerializedName("instructions")
            val instructions: List<String>,
            @SerializedName("label")
            val label: String,
            @SerializedName("mealType")
            val mealType: List<String>,
            @SerializedName("shareAs")
            val shareAs: String,
            @SerializedName("source")
            val source: String,
            @SerializedName("tags")
            val tags: List<String>,
            @SerializedName("totalCO2Emissions")
            val totalCO2Emissions: Int,
            @SerializedName("totalDaily")
            val totalDaily: TotalDaily,
            @SerializedName("totalNutrients")
            val totalNutrients: TotalNutrients,
            @SerializedName("totalWeight")
            val totalWeight: Double,
            @SerializedName("uri")
            val uri: String,
            @SerializedName("url")
            val url: String,
            @SerializedName("yield")
            val yield: Int
        ) {
            data class Digest(
                @SerializedName("daily")
                val daily: Double,
                @SerializedName("hasRDI")
                val hasRDI: Boolean,
                @SerializedName("label")
                val label: String,
                @SerializedName("schemaOrgTag")
                val schemaOrgTag: String,
                //@SerializedName("sub")
                //val sub: String,
                @SerializedName("tag")
                val tag: String,
                @SerializedName("total")
                val total: Double,
                @SerializedName("unit")
                val unit: String
            )

            data class Images(
                @SerializedName("LARGE")
                val lARGE: LARGE,
                @SerializedName("REGULAR")
                val rEGULAR: REGULAR,
                @SerializedName("SMALL")
                val sMALL: SMALL,
                @SerializedName("THUMBNAIL")
                val tHUMBNAIL: THUMBNAIL
            ) {
                data class LARGE(
                    @SerializedName("height")
                    val height: Int,
                    @SerializedName("url")
                    val url: String,
                    @SerializedName("width")
                    val width: Int
                )

                data class REGULAR(
                    @SerializedName("height")
                    val height: Int,
                    @SerializedName("url")
                    val url: String,
                    @SerializedName("width")
                    val width: Int
                )

                data class SMALL(
                    @SerializedName("height")
                    val height: Int,
                    @SerializedName("url")
                    val url: String,
                    @SerializedName("width")
                    val width: Int
                )

                data class THUMBNAIL(
                    @SerializedName("height")
                    val height: Int,
                    @SerializedName("url")
                    val url: String,
                    @SerializedName("width")
                    val width: Int
                )
            }

            data class Ingredient(
                @SerializedName("food")
                val food: String,
                @SerializedName("foodId")
                val foodId: String,
                @SerializedName("measure")
                val measure: String,
                @SerializedName("quantity")
                val quantity: Float,
                @SerializedName("text")
                val text: String,
                @SerializedName("weight")
                val weight: Float
            )

            data class TotalDaily(
                @SerializedName("additionalProp1")
                val additionalProp1: AdditionalProp1,
                @SerializedName("additionalProp2")
                val additionalProp2: AdditionalProp2,
                @SerializedName("additionalProp3")
                val additionalProp3: AdditionalProp3
            ) {
                data class AdditionalProp1(
                    @SerializedName("label")
                    val label: String,
                    @SerializedName("quantity")
                    val quantity: Int,
                    @SerializedName("unit")
                    val unit: String
                )

                data class AdditionalProp2(
                    @SerializedName("label")
                    val label: String,
                    @SerializedName("quantity")
                    val quantity: Int,
                    @SerializedName("unit")
                    val unit: String
                )

                data class AdditionalProp3(
                    @SerializedName("label")
                    val label: String,
                    @SerializedName("quantity")
                    val quantity: Int,
                    @SerializedName("unit")
                    val unit: String
                )
            }

            data class TotalNutrients(
                @SerializedName("additionalProp1")
                val additionalProp1: AdditionalProp1,
                @SerializedName("additionalProp2")
                val additionalProp2: AdditionalProp2,
                @SerializedName("additionalProp3")
                val additionalProp3: AdditionalProp3
            ) {
                data class AdditionalProp1(
                    @SerializedName("label")
                    val label: String,
                    @SerializedName("quantity")
                    val quantity: Int,
                    @SerializedName("unit")
                    val unit: String
                )

                data class AdditionalProp2(
                    @SerializedName("label")
                    val label: String,
                    @SerializedName("quantity")
                    val quantity: Int,
                    @SerializedName("unit")
                    val unit: String
                )

                data class AdditionalProp3(
                    @SerializedName("label")
                    val label: String,
                    @SerializedName("quantity")
                    val quantity: Int,
                    @SerializedName("unit")
                    val unit: String
                )
            }
        }
    }

    data class Links(
        @SerializedName("next")
        val next: Next,
        @SerializedName("self")
        val self: Self
    ) {
        data class Next(
            @SerializedName("href")
            val href: String,
            @SerializedName("title")
            val title: String
        )

        data class Self(
            @SerializedName("href")
            val href: String,
            @SerializedName("title")
            val title: String
        )
    }
}
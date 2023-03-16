package com.maquincher.yaperecipes.models

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

fun <A> A.toJson(): String? {
    return Gson().toJson(this)
}

data class Recipe(
    val id: Int,
    val name: String,
    val latlng: LatLng,
    val description: String,
    val ingredients: List<String> = emptyList(),
    @SerializedName("img_src") val imgSrcUrl: String
)


data class LatLng(
    val lat:BigDecimal,
    val lng:BigDecimal
)

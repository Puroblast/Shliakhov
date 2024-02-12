package com.puroblast.tintest.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

@Entity
data class Film(
    @SerializedName(value = "filmId" , alternate = ["kinopoiskId"])
    @PrimaryKey
    val filmId : Int,
    val nameRu : String,
    val posterUrl : String,
    val posterUrlPreview : String,
    val year : Int,
    val genres : List<Genre>,
    val countries : List<Country>,
    var description : String? = null,
    var isFavourite : Boolean = false,
    var position : Int? = null

)

package com.puroblast.tintest.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Film(
    @PrimaryKey
    @SerializedName(value = "filmId" , alternate = ["kinopoiskId"])
    val filmId : Int,
    val nameRu : String,
    val posterUrl : String,
    val posterUrlPreview : String,
    val year : Int,
    val genres : List<Genre>,
    val countries : List<Country>,
    var description : String? = null,
    val isFavourite : Boolean = false
)

package com.puroblast.tintest.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Film(
    @PrimaryKey
    val filmId : Int,
    val nameRu : String,
    val posterUrl : String,
    val posterUrlPreview : String,
    val year : Int,
    val genres : List<Genre>,
    val countries : List<Country>,
    val description : String,
    val isFavourite : Boolean
)

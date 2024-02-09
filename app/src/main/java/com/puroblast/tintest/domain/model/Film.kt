package com.puroblast.tintest.domain.model

data class Film(
    val filmId : Int,
    val nameRu : String,
    val posterUrl : String,
    val posterUrlPreview : String,
    val year : Int,
    val genres : List<Genre>,
    val countries : List<Country>
)

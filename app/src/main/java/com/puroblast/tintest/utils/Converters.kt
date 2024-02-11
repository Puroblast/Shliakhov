package com.puroblast.tintest.utils

import androidx.room.TypeConverter
import com.puroblast.tintest.domain.model.Country
import com.puroblast.tintest.domain.model.Genre

class Converters {

    @TypeConverter
    fun convertGenresListToString (list : List<Genre>) : String {
        return list.joinToString(", ") { it.genre }
    }
    @TypeConverter
    fun convertCountriesListToString (list : List<Country>) : String {
        return list.joinToString(", ") { it.country }
    }

    @TypeConverter
    fun convertStringToGenresList(string: String) : List<Genre> {
        return string.split(", ").map { Genre(genre = it)}
    }
    @TypeConverter
    fun convertStringToCountriesList(string: String) : List<Country> {
        return string.split(", ").map { Country(country = it) }
    }

}

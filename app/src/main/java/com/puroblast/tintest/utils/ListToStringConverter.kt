package com.puroblast.tintest.utils

import android.util.Log
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.puroblast.tintest.domain.model.Country
import com.puroblast.tintest.domain.model.Genre

class ListToStringConverter {

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
        Log.d("TAG", "convertStringToGenresList: $string")
        return string.split(", ").map { Genre(genre = it)}
    }
    @TypeConverter
    fun convertStringToCountriesList(string: String) : List<Country> {
        return string.split(", ").map { Country(country = it) }
    }
}

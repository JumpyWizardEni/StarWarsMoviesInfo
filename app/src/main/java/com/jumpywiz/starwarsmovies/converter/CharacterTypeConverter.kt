package com.jumpywiz.starwarsmovies.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class CharacterTypeConverter {

    @TypeConverter
    fun fromURL(characters: List<String?>): String? {
        val gson = Gson()
        val type: Type = object : TypeToken<MutableList<String>>() {}.type
        return gson.toJson(characters, type)
    }

    @TypeConverter
    fun toURL(characters: String): List<String?> {
        val gson = Gson()
        val type =
            object : TypeToken<MutableList<String?>>() {}.type
        return gson.fromJson<MutableList<String?>>(characters, type)
    }
}
package com.androiddevs.internationalnews.Database

import androidx.room.TypeConverter
import com.androiddevs.internationalnews.Source

class Converters {

    @TypeConverter
    fun fromSourceToString(source: Source) : String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source{
        return Source(name, name)
    }
}
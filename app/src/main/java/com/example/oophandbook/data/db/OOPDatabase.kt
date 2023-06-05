package com.example.oophandbook.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.oophandbook.data.db.dao.BookmarkDao
import com.example.oophandbook.data.db.dao.ContentDao
import com.example.oophandbook.data.db.dao.TopicDao
import com.example.oophandbook.data.db.dao.UnitDao
import com.example.oophandbook.data.db.model.BookmarkEntity
import com.example.oophandbook.data.db.model.ContentEntity
import com.example.oophandbook.data.db.model.TopicEntity
import com.example.oophandbook.data.db.model.UnitEntity
import com.example.oophandbook.data.db.model.converter.ContentTypeConverter

@Database(
    entities = [
        BookmarkEntity::class,
        ContentEntity::class,
        TopicEntity::class,
        UnitEntity::class
    ],
    version = 1
)
@TypeConverters(ContentTypeConverter::class)
abstract class OOPDatabase : RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao
    abstract fun contentDao(): ContentDao
    abstract fun topicDao(): TopicDao
    abstract fun unitDao(): UnitDao
}
package com.example.oophandbook.data.db.callback

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.oophandbook.data.db.OOPDatabase
import com.example.oophandbook.data.json.model.DataJson
import com.example.oophandbook.data.mapper.toEntity
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

class PrepopulateDatabaseCallback(private val context: Context) : RoomDatabase.Callback() {
    @OptIn(ExperimentalStdlibApi::class)
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        Executors.newSingleThreadExecutor().execute {
            val jsonString = readJsonFrom("sample.json")
            val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
            val data = moshi.adapter<DataJson>().fromJson(jsonString)!!
            val database = createDb()

            runBlocking(Dispatchers.IO) {
                data.topics.forEach { topic ->
                    val topicId = database.topicDao().insert(topic.toEntity()).toInt()

                    topic.units.forEach { unit ->
                        val unitId = database.unitDao().insert(unit.toEntity(topicId)).toInt()

                        unit.content.forEach { content ->
                            database.contentDao().insert(content.toEntity(unitId))
                        }
                    }
                }
            }
        }
    }

    private fun createDb() = Room.databaseBuilder(
        context = context,
        klass = OOPDatabase::class.java,
        name = "oop_database"
    ).build()


    private fun readJsonFrom(fileName: String) = context.assets.open(fileName).reader().readText()
}
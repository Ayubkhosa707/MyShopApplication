package com.ayub.khosa.myshopapplication.room


import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import android.content.Context
import com.ayub.khosa.myshopapplication.model.CATEGORY
import com.ayub.khosa.myshopapplication.model.PRODUCT
import com.ayub.khosa.myshopapplication.model.USER


@Database(
    entities = [PRODUCT::class, CATEGORY::class, USER::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun shopDAO(): ShopDAO

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, "myshop.db"
                    ).allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}

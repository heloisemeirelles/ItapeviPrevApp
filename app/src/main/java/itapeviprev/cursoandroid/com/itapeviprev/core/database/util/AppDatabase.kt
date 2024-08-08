package itapeviprev.cursoandroid.com.itapeviprev.core.database.util

import androidx.room.Database
import androidx.room.RoomDatabase
import itapeviprev.cursoandroid.com.itapeviprev.core.database.dao.UserDao
import itapeviprev.cursoandroid.com.itapeviprev.core.database.model.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
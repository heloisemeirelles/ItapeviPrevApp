package itapeviprev.cursoandroid.com.itapeviprev.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import itapeviprev.cursoandroid.com.itapeviprev.core.database.model.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table")
    fun getAll(): List<UserEntity>

    @Query("SELECT * FROM user_table WHERE email IN (:email)")
    fun findByEmail(email: String): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: UserEntity)

    @Query("DELETE FROM user_table WHERE email IN (:email)")
    fun deleteUserByEmail(email: String)

    @Query("UPDATE user_table SET dateOfBirth = :dateOfBirth, fullName = :fullName  WHERE email = :email")
    fun updateSelectedFundName(dateOfBirth: String, fullName: String, email: String)

}
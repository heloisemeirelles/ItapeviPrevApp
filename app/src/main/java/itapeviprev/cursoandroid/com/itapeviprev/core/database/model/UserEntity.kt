package itapeviprev.cursoandroid.com.itapeviprev.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "user_table"
)
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    val fullName: String?,
    val dateOfBirth: String?,
    val identification: Int?,
    val email: String?
)

object UserIdentification {
    const val RETIREE = 0
    const val PENSIONER = 1
    const val OTHER = 2
}


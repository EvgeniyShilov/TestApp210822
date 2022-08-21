package by.solveit.testapp210822.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [UserEntity::class, PostEntity::class],
    views = [UserPreviewData::class],
    exportSchema = false,
    version = 1
)
abstract class DB : RoomDatabase() {

    abstract fun dao(): DAO
}

package by.solveit.testapp210822.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DAO {

    @Transaction
    suspend fun insertAll(
        users: List<UserEntity>,
        posts: List<PostEntity>
    ) {
        insertUsers(users)
        insertPosts(posts)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(
        users: List<UserEntity>
    )

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(
        posts: List<PostEntity>
    )

    @Query(
        """
            SELECT * FROM UserPreviewData
            ORDER BY postCount DESC
            LIMIT :limit
        """
    )
    fun userPreviewData(
        limit: Int = Int.MAX_VALUE
    ): Flow<List<UserPreviewData>>

    @Transaction
    @Query(
        """
            SELECT * FROM UserEntity AS u
            WHERE u.id = :userId
            LIMIT 1
        """
    )
    fun userDetailsData(
        userId: Long
    ): Flow<List<UserDetailsData>>
}

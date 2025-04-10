package com.anucodes.lokal_assessment.bookmarkJob

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.anucodes.lokal_assessment.networking.model.Result


@Dao
interface JobDao {

    @Upsert
    suspend fun addBookmark(job: BookmarkEntity)

    @Query("SELECT * FROM BookmarkEntity")
    suspend fun getBookmarkJobs(): List<BookmarkEntity>

    @Delete
    suspend fun deleteBookmarkJob(job: BookmarkEntity)
}
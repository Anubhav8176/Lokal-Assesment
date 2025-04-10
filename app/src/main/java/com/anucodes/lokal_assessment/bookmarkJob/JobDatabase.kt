package com.anucodes.lokal_assessment.bookmarkJob

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [BookmarkEntity::class],
    version = 1
)
abstract class JobDatabase: RoomDatabase() {
    abstract fun dao(): JobDao
}
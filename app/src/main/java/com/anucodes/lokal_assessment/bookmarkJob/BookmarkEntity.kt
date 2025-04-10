package com.anucodes.lokal_assessment.bookmarkJob

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class BookmarkEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val location: String,
    val phoneData: String,
    val salary: String,
    val expiredOn: String
)

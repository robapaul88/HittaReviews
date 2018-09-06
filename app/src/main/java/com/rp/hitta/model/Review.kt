package com.rp.hitta.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by roba
 */
@Entity(tableName = "review")
class Review(var userName: String?,
             var avatar: String?,
             @ColumnInfo(name = "created_at")
             var createdAt: Long = 0,
             var rating: Int = 0,
             var source: String?,
             var content: String?,
             val isCurrentUser: Int? = 0) {
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
}

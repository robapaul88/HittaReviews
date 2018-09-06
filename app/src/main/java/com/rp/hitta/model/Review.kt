package com.rp.hitta.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by roba
 */
@Entity
class Review(val userName: String?,
             val avatar: String?,
             @ColumnInfo(name = "created_at")
             val createdAt: Long = 0,
             val rating: Int = 0,
             val source: String?,
             val content: String?,
             val isCurrentUser: Int? = 0) {
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
}

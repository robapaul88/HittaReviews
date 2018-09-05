package com.rp.hitta.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by roba
 */
@Entity
class Review(private var name: String?, avatar: String?, createdAt: Long = 0, rating: Int = 0, source: String?, content: String?) {
    @PrimaryKey(autoGenerate = true)
    private val id: Int = 0

    private val avatar: String? = null
    @ColumnInfo(name = "created_at")
    private val createdAt: Long = 0
    private val rating: Int = 0
    private val source: String? = null
    private val content: String? = null
}

package com.example.lab8.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @PrimaryKey(autoGenerate = true) var uid: Int = 0,
    @ColumnInfo(name = "tenphim") var tenphim: String?,
    @ColumnInfo(name = "thoiluong") var thoiluong: String?,
    @ColumnInfo(name = "img") var img: String?,
    @ColumnInfo(name = "noidung") var noidung: String?,
    @ColumnInfo(name = "quocgia") var quocgia: String?,
    @ColumnInfo(name = "namphathanh") var namphathanh: String?,
)

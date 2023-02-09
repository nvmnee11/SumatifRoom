package com.example.sumatifroom_nabhan_qori_albana_ganjil.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class tb_karyawan (
    @PrimaryKey
    val id: Int,
    val NAMA: String,
    val ALAMAT:String,
    val USIA : String,
)
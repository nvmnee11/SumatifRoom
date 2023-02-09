package com.example.sumatifroom_nabhan_qori_albana_ganjil.Room

import androidx.room.*

@Dao
interface karyawanDAO {
        @Insert
        fun addtbkary (tbkar: tb_karyawan)

        @Update
        fun updatetbkary (tbkar: tb_karyawan)

        @Delete
        fun deltbkary (tbkar: tb_karyawan)

        @Query("SELECT * FROM tb_karyawan")
        fun tampilall () : List<tb_karyawan>

        @Query("SELECT * FROM tb_karyawan WHERE id=:nis_db")
        fun tampilanall (nis_db: Int) : List<tb_karyawan>

    }
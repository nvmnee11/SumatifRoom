package com.example.sumatifroom_nabhan_qori_albana_ganjil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sumatifroom_nabhan_qori_albana_ganjil.Room.codepelita
import com.example.sumatifroom_nabhan_qori_albana_ganjil.Room.tb_karyawan
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    val db by lazy { codepelita(this) }
    private lateinit var karyawanAdapter: KaryawanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        halEdit()
        setuprecyclerview()

    }
    override fun onStart() {
        super.onStart()
        loadData()
    }
    fun loadData() {
        CoroutineScope(Dispatchers.IO).launch {
            val karyawan = db.KRYWNDAO().tampilall()
            Log.d("MainActivity", "dbResponse:$karyawan")
            withContext(Dispatchers.Main) {
                karyawanAdapter.setData(karyawan)
            }
        }
    }
    fun intentedit(tbKaryawanid: Int, intentType: Int) {
        startActivity(
            Intent(applicationContext, EditActivity::class.java)
                .putExtra("intent_id", tbKaryawanid)
                .putExtra("intent_type", intentType)
        )
    }
    private fun halEdit() {
        btninput.setOnClickListener{
            intentedit(0,Constant.TYPE_CREATE)
        }
    }

    private fun setuprecyclerview() {
        karyawanAdapter = KaryawanAdapter(arrayListOf(), object : KaryawanAdapter.onAdapterListener{
            override fun onClick(tbkar: tb_karyawan) {
                intentedit(tbkar.id, Constant.TYPE_READ)
            }
            override fun onUp(tbkar: tb_karyawan) {
                intentedit(tbkar.id, Constant.TYPE_UPDATE)
            }

            override fun onDel(tbkar: tb_karyawan) {
                deletalert(tbkar)
            }
        })
        listData.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = karyawanAdapter
        }
    }

private fun deletalert(tbkar: tb_karyawan) {
    val dialog = AlertDialog.Builder(this)
    dialog.apply {
        setTitle("KOnfirmasi Hapus")
        setMessage("Yakin Mau HApus ${tbkar.NAMA}")
        setNegativeButton("Batal") {DialogInterface, i ->
            DialogInterface.dismiss()
    }
        setPositiveButton("Hapus"){DialogInterface, i ->
            CoroutineScope(Dispatchers.IO).launch {
                db.KRYWNDAO().deltbkary(tbkar)
                DialogInterface.dismiss()
                loadData()
            }
        }
    }
    dialog.show()
}
}
package com.example.sumatifroom_nabhan_qori_albana_ganjil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.sumatifroom_nabhan_qori_albana_ganjil.Room.codepelita
import com.example.sumatifroom_nabhan_qori_albana_ganjil.Room.tb_karyawan
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {
    val db by lazy { codepelita(this)}
    private var tbkaryawanid: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        tombolperintah()
        setupView()
        Toast.makeText(this,tbkaryawanid.toString(),Toast.LENGTH_SHORT).show()

    }

    private fun setupView() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type",0)
        when (intentType) {
            Constant.TYPE_CREATE -> {
                btUp.visibility = View.GONE
            }
            Constant.TYPE_READ -> {
                btUp.visibility = View.GONE
                btSimpan.visibility = View.GONE
                ETid.visibility = View.GONE
                tampildataId()
            }
            Constant.TYPE_UPDATE -> {
                btSimpan.visibility = View.GONE
                ETid.visibility = View.GONE
                tampildataId()
            }
        }
    }

    fun tombolperintah() {
            btSimpan.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    db.KRYWNDAO().addtbkary(
                        tb_karyawan(
                        ETid.text.toString().toInt(),
                        ETnm.text.toString(),
                        ETAlamat.text.toString(),
                        ETUS.text.toString()
                        )
                    )
                    finish()
                }
            }
            btUp.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    db.KRYWNDAO().updatetbkary(
                        tb_karyawan(
                            tbkaryawanid,
                            ETnm.text.toString(),
                            ETAlamat.text.toString(),
                            ETUS.text.toString(),
                        )
                    )
                    finish()
                }
            }
        }
    fun tampildataId() {
        tbkaryawanid = intent.getIntExtra("intent_id",0)
        CoroutineScope(Dispatchers.IO).launch {
            val tenagakerja = db.KRYWNDAO().tampilanall(tbkaryawanid).get(0)
            val dataId: String = tenagakerja.id.toString()
            ETid.setText(dataId)
            ETnm.setText(tenagakerja.NAMA)
            ETAlamat.setText(tenagakerja.ALAMAT)
            ETUS.setText(tenagakerja.USIA)

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true

    }

    }
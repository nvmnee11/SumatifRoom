package com.example.sumatifroom_nabhan_qori_albana_ganjil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sumatifroom_nabhan_qori_albana_ganjil.Room.tb_karyawan
import kotlinx.android.synthetic.main.activity_edit.view.*
import kotlinx.android.synthetic.main.adapter_karyawan.view.*

class KaryawanAdapter (private val karyawan :ArrayList<tb_karyawan>, private val listener: onAdapterListener)
    : RecyclerView.Adapter<KaryawanAdapter.KaryawanViewHolder>() {
    class KaryawanViewHolder (val view: View) : RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KaryawanViewHolder {
        return KaryawanViewHolder(
            LayoutInflater.from(parent.context).inflate (R.layout.adapter_karyawan,parent,false)
        )
    }

    override fun onBindViewHolder(holder: KaryawanViewHolder, position: Int) {
        val kry = karyawan[position]
        holder.view.tvid.text = kry.id.toString()
        holder.view.tvnm.text = kry.NAMA
        holder.view.imgbg.setOnClickListener{listener.onClick(kry)}
        holder.view.imgedit.setOnClickListener{listener.onUp(kry)}
        holder.view.imgdel.setOnClickListener{listener.onDel(kry)}
    }

    override fun getItemCount() = karyawan.size

    fun setData(list: List<tb_karyawan>){
        karyawan.clear()
        karyawan.addAll(list)
        notifyDataSetChanged()
    }
    interface onAdapterListener{
        fun onClick(tbkar: tb_karyawan)
        fun onUp(tbkar: tb_karyawan)
        fun onDel(tbkar: tb_karyawan)
    }

}


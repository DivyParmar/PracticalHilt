package com.practical.divy.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.practical.divy.databinding.CustomProgressbarBinding


class DemoAdapter(val onClick: (data: String) -> Unit) :
    RecyclerView.Adapter<DemoAdapter.ViewHolder>() {

    private var list: ArrayList<String> = ArrayList()
    private lateinit var mcontext: Context

    class ViewHolder(val binding: CustomProgressbarBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: String) {
            binding.apply {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mcontext = parent.context
        return ViewHolder(
            CustomProgressbarBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount() = list.size

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

    fun addData(list: ArrayList<String>?) {
        if (list != null) {
            this.list.addAll(list)
        }
        notifyDataSetChanged()
    }


    fun addItem(question: String) {
        this.list.add(question)
        notifyItemInserted(list.size - 1)
    }


    fun getList(): ArrayList<String> {
        return list
    }
}
package com.example.taskify

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView


class CustomAdapter(
    private val activity: Activity,
    private val context: Context,
    private val task_id: ArrayList<*>,
    private val task_title: ArrayList<*>,
    private val task_description: ArrayList<*>,
) : RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.my_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.task_id_txt.text = task_id[position].toString()
        holder.task_title_txt.text = task_title[position].toString()
        holder.task_description_txt.text = task_description[position].toString()

        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener {
            val intent = Intent(context, UpdateActivity::class.java)
            intent.putExtra("id", task_id[position].toString())
            intent.putExtra("title", task_title[position].toString())
            intent.putExtra("description", task_description[position].toString())
            activity.startActivityForResult(intent, 1)
        }
    }

    override fun getItemCount(): Int {
        return task_id.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var task_id_txt: TextView
        var task_title_txt: TextView
        var task_description_txt: TextView
        var mainLayout: LinearLayout

        init {
            task_id_txt = itemView.findViewById<TextView>(R.id.task_id_txt)
            task_title_txt = itemView.findViewById<TextView>(R.id.task_title_txt)
            task_description_txt = itemView.findViewById<TextView>(R.id.task_description_txt)
            mainLayout = itemView.findViewById<LinearLayout>(R.id.mainLayout)
            //Animate Recyclerview
            val translate_anim = AnimationUtils.loadAnimation(
                itemView.context, R.anim.translate_anim
            )
            mainLayout.setAnimation(translate_anim)
        }
    }
}


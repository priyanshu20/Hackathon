package com.dsckiet.hackathonhelpapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dsckiet.hackathonhelpapp.R
import kotlinx.android.synthetic.main.item_view.view.*

class GeneralHelpAdapter(val ctx: Context, private val listItem: List<String>) :
    RecyclerView.Adapter<GeneralHelpAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(
            itemView
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = listItem[position]

        holder.cardView.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_volunteerFragment_to_helpVaultFragment)
        }
        //sending patient details to patient details activity

    }

    override fun getItemCount(): Int = listItem.size

    class ViewHolder(unitView: View) : RecyclerView.ViewHolder(unitView) {
        val helpName: TextView =unitView.help_name
        val helpDescription : TextView =unitView.help_description
        val cardView: CardView =unitView.card_view

    }
}
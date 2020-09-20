package com.dsckiet.hackathonhelpapp.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.dsckiet.hackathonhelpapp.R
import com.dsckiet.hackathonhelpapp.model.Data
import kotlinx.android.synthetic.main.item_view.view.*

class EmergencyHelpAdapter(private val ctx: Context, private val listItem: List<Data>) :
    RecyclerView.Adapter<EmergencyHelpAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(
            itemView
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = listItem[position]
        holder.helpName.text = currentItem.tag
        holder.helpDescription.text = currentItem.description
        val lat = currentItem.location?.coordinates?.get(0)!!
        val lon = currentItem.location.coordinates[1]
        holder.cardView.setOnClickListener {
            Toast.makeText(ctx, "Opening Google Maps Application", Toast.LENGTH_SHORT)
                .show()
            val gmmIntentUri = Uri.parse("geo:$lat,$lon")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            ctx.startActivity(mapIntent)
        }

        //sending patient details to patient details activity

    }

    override fun getItemCount(): Int = listItem.size

    class ViewHolder(unitView: View) : RecyclerView.ViewHolder(unitView) {
        val helpName: TextView = unitView.help_name
        val helpDescription: TextView = unitView.help_description
        val cardView: CardView = unitView.card_view

    }
}
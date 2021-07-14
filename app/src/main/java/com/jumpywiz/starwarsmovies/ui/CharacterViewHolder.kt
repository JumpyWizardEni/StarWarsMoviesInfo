package com.jumpywiz.starwarsmovies.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jumpywiz.starwarsmovies.R

class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val name = itemView.findViewById(R.id.character_name_text) as TextView
    val sex = itemView.findViewById(R.id.sex_text) as TextView
    val birthDate = itemView.findViewById(R.id.birth_date_text) as TextView

}
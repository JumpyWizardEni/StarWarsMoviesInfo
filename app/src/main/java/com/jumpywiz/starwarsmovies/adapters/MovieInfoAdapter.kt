package com.jumpywiz.starwarsmovies.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jumpywiz.starwarsmovies.R
import com.jumpywiz.starwarsmovies.ui.CharacterViewHolder
import com.jumpywiz.starwarsmovies.model.Character
import com.jumpywiz.starwarsmovies.ui.CharacterClickListener

class MovieInfoAdapter(private val nav: (Int, Bundle?) -> Unit) : RecyclerView.Adapter<CharacterViewHolder>() {
    private var chars: MutableList<Character> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.character_view_holder, parent, false)
        return CharacterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val char = chars[position]
        holder.name.text = char.name
        holder.sex.text = char.sex
        holder.birthDate.text = char.birthDate
        
        val listener = CharacterClickListener(char.name, char.url, nav)
        holder.itemView.setOnClickListener(listener)
    }

    override fun getItemCount(): Int {
        return chars.size
    }

    fun setData(newChars: List<Character>) {
        chars = mutableListOf()
        chars.addAll(newChars)
        notifyDataSetChanged()
    }



}
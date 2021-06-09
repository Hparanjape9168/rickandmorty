package com.hardik.rickandmorty.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hardik.rickandmorty.R
import com.hardik.rickandmorty.databinding.ItemCharacterListBinding
import com.hardik.rickandmorty.model.CharacterModel
import com.hardik.rickandmorty.utils.ClickListener

class CharacterListAdapter(private val characterList: ArrayList<CharacterModel>) :
        RecyclerView.Adapter<CharacterListAdapter.CharacterViewHolder>(), ClickListener{

    class CharacterViewHolder(var view: ItemCharacterListBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemCharacterListBinding>(inflater, R.layout.item_character_list,parent,false)
        return CharacterViewHolder(view)
    }

    override fun getItemCount() = characterList.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.view.character = characterList[position]
        holder.view.listener = this
    }

    override fun onClickListener(view: View) {
       Log.d("Hardik:","onItemClick")
    }

    fun updateList(newList: List<CharacterModel>) {
        characterList.clear()
        characterList.addAll(newList)
        notifyDataSetChanged()
    }
}
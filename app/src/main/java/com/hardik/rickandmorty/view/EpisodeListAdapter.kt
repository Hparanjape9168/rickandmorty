package com.hardik.rickandmorty.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hardik.rickandmorty.R
import com.hardik.rickandmorty.databinding.ItemEpisodeBinding
import com.hardik.rickandmorty.model.EpisodeModel

class EpisodeListAdapter(private val list : ArrayList<EpisodeModel>) :
    RecyclerView.Adapter<EpisodeListAdapter.EpisodeViewHolder>(){

    class EpisodeViewHolder(var view: ItemEpisodeBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemEpisodeBinding>(inflater, R.layout.item_episode,parent,false)
        return EpisodeViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.view.episode = list[position]
    }

    fun updateList(newList: List<EpisodeModel>?) {
        newList?.let {
            list.clear()
            list.addAll(it)
            notifyDataSetChanged()
        }
    }
}
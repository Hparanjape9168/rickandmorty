package com.hardik.rickandmorty.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hardik.rickandmorty.R
import com.hardik.rickandmorty.model.EpisodeModel
import com.hardik.rickandmorty.utils.Constant
import com.hardik.rickandmorty.viewmodel.EpisodeViewModel
import kotlinx.android.synthetic.main.activity_episode_list.*

class EpisodeListActivity : AppCompatActivity() {

    private lateinit var episodeViewModel: EpisodeViewModel
    private var episodeListAdapter = EpisodeListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episode_list)
        episodeViewModel = ViewModelProvider(this).get(EpisodeViewModel::class.java)
        val list = intent?.extras?.getIntArray(Constant.EXTRA_KEY) ?: return
        if(urlConverter(list).isNullOrEmpty())
            return

        episodeViewModel.refreshData(urlConverter(list))
        episodeListRecyclerView.apply {
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = episodeListAdapter
        }
        subscribeData()
    }

    private fun subscribeData(){
        episodeViewModel.episodeList.observe(this, Observer {list->
            episodeListAdapter.updateList(list)
        })
    }

    private fun urlConverter(array: IntArray) : String {
        var localUrl = ""
        array.forEach {
            localUrl+= "$it,"
        }
        return localUrl
    }
}
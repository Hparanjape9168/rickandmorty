package com.hardik.rickandmorty.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hardik.rickandmorty.R
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
        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
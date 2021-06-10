package com.hardik.rickandmorty.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hardik.rickandmorty.R
import com.hardik.rickandmorty.databinding.ActivityCharacterDetailBinding
import com.hardik.rickandmorty.model.CharacterDetailsModel
import com.hardik.rickandmorty.utils.ClickListener
import com.hardik.rickandmorty.utils.Constant
import com.hardik.rickandmorty.utils.Constant.NAVIGATE_TAG
import com.hardik.rickandmorty.viewmodel.CharacterDetailsViewModel

class CharacterDetailActivity : AppCompatActivity(), ClickListener {

    private lateinit var binding: ActivityCharacterDetailBinding
    private lateinit var characterDetailsViewModel: CharacterDetailsViewModel
    private lateinit var episodesList : IntArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_character_detail)
        characterDetailsViewModel = ViewModelProvider(this).get(CharacterDetailsViewModel::class.java)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        binding.listener = this
        viewVisibility(true)
        val charsId = intent?.extras?.getString(Constant.EXTRA_KEY)
        charsId?.let {
            characterDetailsViewModel.callCharacterDetailsAPI(charsId.toInt())
        }
        Log.d(Constant.TAG+"CharacterDetailActivity ",""+charsId)

        observerData()
    }

   private fun observerData(){
        characterDetailsViewModel.characterDetails.observe(this, Observer { characterDetailsModel ->
            binding.details = characterDetailsModel
            episodesList = episodeListConvertToId(characterDetailsModel)
        })

       characterDetailsViewModel.locationModel.observe(this, Observer { locationModel->
           binding.location = locationModel
       })
    }

    private fun episodeListConvertToId(characterDetailsModel: CharacterDetailsModel): IntArray{
        val finalList = characterDetailsModel.episode.map {episode->
             Uri.parse(episode).lastPathSegment!!.let {id->
                 id.toInt()
             }
        }
        return finalList.toIntArray()
    }
    override fun onClickListener(view: View) {
        if(view.tag == NAVIGATE_TAG && episodesList.isNotEmpty()){
            val intent = Intent(this,EpisodeListActivity::class.java)
            intent.putExtra(Constant.EXTRA_KEY,episodesList)
            startActivity(intent)
            return
        }

        if(!Constant.layoutVisible.value!!){
            viewVisibility(false)
            Constant.layoutVisible.value = true
            val stringSplit = Uri.parse(view.tag.toString())
            Log.d(Constant.TAG,"Location Click:${stringSplit.lastPathSegment}")
            stringSplit.lastPathSegment?.let {
                characterDetailsViewModel.callLocationAPI(it.toInt())
            }
        } else {
            viewVisibility(true)
            Constant.layoutVisible.value = false
        }
    }

    private fun viewVisibility(isVisible : Boolean){
        if(isVisible){
            // DropDown Close
            binding.textViewLocationMain.visibility = View.VISIBLE
            binding.textViewLocationDetails.visibility = View.VISIBLE
            binding.linearLayoutDropdown.visibility = View.GONE
        }
        else{
            // DropDown Open
            binding.textViewLocationMain.visibility = View.GONE
            binding.textViewLocationDetails.visibility = View.GONE
            binding.linearLayoutDropdown.visibility = View.VISIBLE
        }
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
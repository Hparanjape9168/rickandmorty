package com.hardik.rickandmorty.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hardik.rickandmorty.R
import com.hardik.rickandmorty.databinding.ActivityCharacterDetailBinding
import com.hardik.rickandmorty.utils.ClickListener
import com.hardik.rickandmorty.utils.Constant
import com.hardik.rickandmorty.viewmodel.CharacterDetailsViewModel

class CharacterDetailActivity : AppCompatActivity(), ClickListener {

    private lateinit var binding: ActivityCharacterDetailBinding
    private lateinit var characterDetailsViewModel: CharacterDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_character_detail)
        characterDetailsViewModel = ViewModelProvider(this).get(CharacterDetailsViewModel::class.java)
        binding.listener = this
        val charsId = intent?.extras?.getString(Constant.EXTRA_KEY)
        charsId?.let {
            characterDetailsViewModel.callCharacterDetailsAPI(charsId.toInt())
        }
        Log.d("Hardik:: CharacterDetailActivity ",""+charsId)
        binding.textViewLocationMain.visibility = View.VISIBLE
        binding.textViewLocationDetails.visibility = View.VISIBLE
        binding.linearLayoutDropdown.visibility = View.GONE
        observerData()
    }

   private fun observerData(){
        characterDetailsViewModel.characterDetails.observe(this, Observer {model ->
            binding.details = model
        })
    }
    override fun onClickListener(view: View) {
        if(!Constant.layoutVisible.value!!){
            Constant.layoutVisible.value = true
            // DropDown Open
            binding.textViewLocationMain.visibility = View.GONE
            binding.textViewLocationDetails.visibility = View.GONE
            binding.linearLayoutDropdown.visibility = View.VISIBLE
        } else {
            // DropDown close
            binding.textViewLocationMain.visibility = View.VISIBLE
            binding.textViewLocationDetails.visibility = View.VISIBLE
            binding.linearLayoutDropdown.visibility = View.GONE
            Constant.layoutVisible.value = false
        }
    }
}
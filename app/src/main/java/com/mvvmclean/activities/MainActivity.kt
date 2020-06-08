package com.mvvmclean.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

import com.domain.entities.MarvelCharacter
import com.mvvmclean.R

import com.mvvmclean.utils.Data
import com.mvvmclean.utils.MINUS_ONE
import com.mvvmclean.utils.Status
import com.mvvmclean.viewmodels.CharacterViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    //try characterId 1011121

    private val viewModel by viewModel<CharacterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.mainState.observe(::getLifecycle, ::updateUI)
        buttonSearchRemote.setOnClickListener{
            onSearchRemoteClicked()
        }
        buttonSearchLocal.setOnClickListener{
            onSearchLocalClicked()
        }
    }

    private fun updateUI(characterData: Data<MarvelCharacter>) {
        when(characterData.responseType) {
            Status.ERROR -> {
                hideProgress()
                characterData.error?.message?.let { showMessage(it) }
                characterData.data?.let { setCharacter(it) }
            }

            Status.LOADING -> {
                showProgress()
            }

            Status.SUCCESSFUL -> {
                hideProgress()
                characterData.data?.let { setCharacter(it)}
            }
        }
    }

    private fun showProgress() {
        progress.visibility = View.VISIBLE
        textViewDetails.visibility = View.GONE
    }

    private fun hideProgress() {
        progress.visibility = View.GONE
        textViewDetails.visibility = View.VISIBLE
    }

    private fun setCharacter(character: MarvelCharacter) {
        textViewDetails.text = character.name
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun onSearchRemoteClicked() {
        val id = if(characterID.text.toString().isNotEmpty()) {
            characterID.text.toString().toInt()
        } else {
            MINUS_ONE
        }

        viewModel.onSearchRemoteClicked(id)
    }

    private fun onSearchLocalClicked() {
        val id = if(characterID.text.toString().isNotEmpty()) {
            characterID.text.toString().toInt()
        } else {
            MINUS_ONE
        }

        viewModel.onSearchLocalClicked(id)
    }
}

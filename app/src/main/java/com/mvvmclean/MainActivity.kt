package com.mvvmclean

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

import com.domain.entities.MarvelCharacter
import com.mvvmclean.di.useCaseModule
import com.mvvmclean.viewmodels.CharacterViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<CharacterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startKoin {
            androidLogger()
            modules(useCaseModule)
        }

        viewModel.mainState.observe(::getLifecycle, ::updateUI)
        buttonSearch.setOnClickListener{
            onSearchClicked()
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

            Status.SUCCESSFULL -> {
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
        textViewDetails.text = character.description
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun onSearchClicked() {
        viewModel.onSearchClicked(characterID.text.toString().toInt())
    }
}

package com.mvvmclean.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.domain.entities.MarvelCharacter
import com.mvvmclean.BaseViewModel
import com.mvvmclean.Data
import com.mvvmclean.Status
import com.mvvmclean.data.FindCharacterImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CharacterViewModel(private val marvelRepo: FindCharacterImpl) : BaseViewModel() {

    private var _mainState: MutableLiveData<Data<MarvelCharacter>> = MutableLiveData()
    val mainState: LiveData<Data<MarvelCharacter>>
        get() {
            return _mainState
        }
    @SuppressLint("CheckedResult")
    fun onSearchClicked(id: Int) {
        _mainState.value = Data(responseType = Status.LOADING)
        marvelRepo.getCharacterById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({character ->
                if(character == null) {
                    _mainState.value = Data(responseType = Status.ERROR)
                } else {
                    _mainState.value = Data(responseType = Status.SUCCESSFULL, data = character)
                }
            },{
                e -> _mainState.value = Data(responseType = Status.ERROR, error = e)
            }

                )

    }

}
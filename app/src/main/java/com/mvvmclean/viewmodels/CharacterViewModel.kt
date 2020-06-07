package com.mvvmclean.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.domain.entities.MarvelCharacter
import com.domain.usecases.GetCharacterByIdUseCase
import com.domain.utils.Result
import com.mvvmclean.viewmodels.base.BaseViewModel
import com.mvvmclean.utils.Data
import com.mvvmclean.utils.Status
import kotlinx.coroutines.*
import kotlinx.coroutines.launch


class CharacterViewModel(val getCharacterById: GetCharacterByIdUseCase): BaseViewModel(    ) {


    private var mutableMainState: MutableLiveData<Data<MarvelCharacter>> = MutableLiveData()
    val mainState: LiveData<Data<MarvelCharacter>>
        get() {
            return mutableMainState
        }


    fun onSearchRemoteClicked(id: Int) = launch {
        mutableMainState.value =
            Data(responseType = Status.LOADING)

            when(val result = withContext(Dispatchers.IO) {getCharacterById(id, true)}) {
                is Result.Failure -> {
                    mutableMainState.value = Data(responseType = Status.ERROR, error = result.exception)
                }
                is Result.Success -> {
                    mutableMainState.value = Data(responseType = Status.SUCCESSFUL, data = result.data)
                }
            }
        }


    fun onSearchLocalClicked(id: Int) = launch{
        mutableMainState.value = Data(responseType = Status.LOADING)

        when(val result = withContext(Dispatchers.IO) {getCharacterById(id, false)}) {
            is Result.Failure -> {
                mutableMainState.value = Data(responseType = Status.ERROR, error = result.exception)
            }
            is Result.Success -> {
                mutableMainState.value = Data(responseType = Status.SUCCESSFUL, data = result.data)
            }
        }
    }
}
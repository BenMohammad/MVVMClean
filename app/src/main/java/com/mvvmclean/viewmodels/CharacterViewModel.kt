package com.mvvmclean.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.domain.entities.MarvelCharacter
import com.domain.usecases.GetCharacterByIdUseCase
import com.domain.utils.Result
import com.mvvmclean.BaseViewModel
import com.mvvmclean.Data
import com.mvvmclean.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class CharacterViewModel: BaseViewModel(SupervisorJob(), Dispatchers.Default) {

    val getCharacterById  = GetCharacterByIdUseCase()

    private var mutableMainState: MutableLiveData<Data<MarvelCharacter>> = MutableLiveData()
    val mainState: LiveData<Data<MarvelCharacter>>
        get() {
            return mutableMainState
        }


    fun onSearchClicked(id: Int) {
        mutableMainState.value = Data(responseType = Status.LOADING)
        viewModelCoroutineScope.launch {
            when(val result = getCharacterById(id)) {
                is Result.Failure -> {
                    mutableMainState.postValue(Data(responseType = Status.ERROR, error = result.exception))
                }

                is Result.Success -> {
                    mutableMainState.postValue(Data(responseType = Status.SUCCESSFUL, data = result.data))
                }
            }
        }
    }
}
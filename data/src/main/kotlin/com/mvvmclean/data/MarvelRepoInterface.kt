package com.mvvmclean.data

import com.domain.entities.MarvelCharacter
import io.reactivex.Observable


interface MarvelRepoInterface {

    fun getCharacterById(id: Int): Observable<MarvelCharacter>

}

package com.mvvmclean.data

import com.domain.entities.MarvelCharacter
import com.mvvmclean.data.mapper.CharacterMapperService
import com.mvvmclean.data.repositories.MarvelCharacterRepository
import io.reactivex.Observable

class FindCharacterImpl(
    private val api: MarvelRequestGenerator = MarvelRequestGenerator(),
    private val mapper: CharacterMapperService = CharacterMapperService()
): MarvelRepoInterface {

    override fun getCharacterById(id: Int): Observable<MarvelCharacter> {
        return Observable.create{

        }
    }
}
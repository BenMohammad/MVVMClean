package com.mvvmclean.data

import com.domain.entities.MarvelCharacter
import com.mvvmclean.data.api.MarvelApi
import com.mvvmclean.data.mapper.CharacterMapperService
import io.reactivex.Observable

class FindCharacterImpl(
    private val api: MarvelRequestGenerator = MarvelRequestGenerator(),
    private val mapper: CharacterMapperService = CharacterMapperService()
): MarvelRepoInterface {

    override fun getCharacterById(id: Int): Observable<MarvelCharacter> {
        return Observable.create{
            subscriber ->
            val callResponse = api.createService(MarvelApi::class.java).getCharacterById(id)
            val response = callResponse.execute()
            if(response.isSuccessful) {
                response.body()?.data?.characters?.get(ZERO)?.let { mapper.transform(it)}
                    ?.let { subscriber.onNext(it)}
                subscriber.onComplete()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}